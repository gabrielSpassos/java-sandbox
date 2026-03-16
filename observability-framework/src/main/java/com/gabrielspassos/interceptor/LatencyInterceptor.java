package com.gabrielspassos.interceptor;

import com.gabrielspassos.annotation.LatencyTracked;
import com.gabrielspassos.collector.LatencyCollector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class LatencyInterceptor {

    private final LatencyCollector latencyCollector;

    public LatencyInterceptor(LatencyCollector latencyCollector) {
        this.latencyCollector = latencyCollector;
    }

    public Object invoke(Object target, Method method, Object[] args) throws InvocationTargetException, IllegalAccessException {
        var annotation = method.getAnnotation(LatencyTracked.class);

        if (null == annotation) {
            return method.invoke(target, args);
        }

        return latencyCollector.measure(annotation.value(), () -> {
            try {
                return method.invoke(target, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }
}
