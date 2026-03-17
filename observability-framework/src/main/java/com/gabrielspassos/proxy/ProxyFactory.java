package com.gabrielspassos.proxy;

import com.gabrielspassos.annotation.LatencyTracked;
import com.gabrielspassos.collector.LatencyCollector;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyFactory {

    @SuppressWarnings("unchecked")
    public static <T> T create(T target, LatencyCollector collector) {
        return (T) Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(), // Requires target interface
                new LatencyInvocationHandler<>(target, collector)
        );
    }

    private static class LatencyInvocationHandler<T> implements InvocationHandler {
        private final T target;
        private final LatencyCollector collector;

        public LatencyInvocationHandler(T target, LatencyCollector collector) {
            this.target = target;
            this.collector = collector;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

            var implMethod = target.getClass()
                    .getMethod(method.getName(), method.getParameterTypes());

            var annotation = implMethod.getAnnotation(LatencyTracked.class);

            if (annotation == null) {
                return method.invoke(target, args);
            }

            return collector.measure(annotation.value(), () -> {
                try {
                    return method.invoke(target, args);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

}
