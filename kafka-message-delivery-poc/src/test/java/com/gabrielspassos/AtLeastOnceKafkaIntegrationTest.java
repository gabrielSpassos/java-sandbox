package com.gabrielspassos;

import com.gabrielspassos.consumer.TestConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(properties = "app.mode=AT_LEAST_ONCE")
class AtLeastOnceKafkaIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    void testAtLeastOnce() throws Exception {
        String message = "error-induction";
        kafkaTemplate.send("delivery-at-least-once-test", message);

        Thread.sleep(5000);

        long count = testConsumer.getProcessedAtLeastOnce().stream()
                .filter(m -> m.equals(message))
                .count();

        assertTrue(count >= 2);
    }
}