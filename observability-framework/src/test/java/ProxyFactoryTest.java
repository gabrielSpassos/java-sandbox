import com.gabrielspassos.collector.LatencyCollector;
import com.gabrielspassos.core.LatencyRegistry;
import com.gabrielspassos.proxy.ProxyFactory;
import com.gabrielspassos.reporter.ConsoleReporter;
import org.junit.jupiter.api.Test;
import service.IPaymentService;
import service.PaymentService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProxyFactoryTest {

    @Test
    void shouldGetMetricsWithProxy() {
        var latencyRegistry = new LatencyRegistry();
        var latencyCollector = new LatencyCollector(latencyRegistry);
        var consoleReporter = new ConsoleReporter();

        IPaymentService paymentService = ProxyFactory.create(new PaymentService(), latencyCollector);

        for (int i = 0; i < 50; i++) {
            List<String> checkout = paymentService.checkoutPayment();

            assertNotNull(checkout);
            assertFalse(checkout.isEmpty());

            String result = paymentService.processPayment();

            assertNotNull(result);
        }

        consoleReporter.report(latencyRegistry);
    }

}