import com.gabrielspassos.collector.LatencyCollector;
import com.gabrielspassos.core.LatencyRegistry;
import com.gabrielspassos.interceptor.LatencyInterceptor;
import com.gabrielspassos.reporter.ConsoleReporter;
import org.junit.jupiter.api.Test;
import service.PaymentService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class LatencyInterceptorTest {

    @Test
    void shouldInterceptAndMeasure() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var latencyRegistry = new LatencyRegistry();
        var latencyCollector = new LatencyCollector(latencyRegistry);
        var latencyInterceptor = new LatencyInterceptor(latencyCollector);
        var consoleReporter = new ConsoleReporter();
        var paymentService = new PaymentService();

        Method checkoutPayment = PaymentService.class.getMethod("checkoutPayment");
        Method processPayment = PaymentService.class.getMethod("processPayment");

        for (int i = 0; i < 50; i++) {
            latencyInterceptor.invoke(paymentService, checkoutPayment, null);
            latencyInterceptor.invoke(paymentService, processPayment, null);
        }

        consoleReporter.report(latencyRegistry);
    }

}