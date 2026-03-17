import com.gabrielspassos.collector.LatencyCollector;
import com.gabrielspassos.core.LatencyRegistry;
import com.gabrielspassos.interceptor.LatencyInterceptor;
import com.gabrielspassos.reporter.ConsoleReporter;
import org.junit.jupiter.api.Test;
import service.PaymentService;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertNotNull;

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
            Object checkout = latencyInterceptor.invoke(paymentService, checkoutPayment, null);
            assertNotNull(checkout);
            Object payment = latencyInterceptor.invoke(paymentService, processPayment, null);
            assertNotNull(payment);
        }

        consoleReporter.report(latencyRegistry);
    }

}