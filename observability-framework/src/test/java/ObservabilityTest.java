import com.gabrielspassos.collector.LatencyCollector;
import com.gabrielspassos.core.LatencyRegistry;
import com.gabrielspassos.reporter.ConsoleReporter;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ObservabilityTest {

    @Test
    void shouldTrackMetrics() {
        var latencyRegister = new LatencyRegistry();
        var collector = new LatencyCollector(latencyRegister);
        var reporter = new ConsoleReporter();
        var random = new Random();

        for (int i = 0; i < 50; i++) {
            collector.measure("database.test.query", () -> {
               try {
                   Thread.sleep(random.nextInt(100));
               } catch (InterruptedException e) {
                   throw new RuntimeException(e);
               }
            });

            collector.measure("test.business.metric", () -> {
                try {
                    Thread.sleep(random.nextInt(80));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        reporter.report(latencyRegister);
    }
}
