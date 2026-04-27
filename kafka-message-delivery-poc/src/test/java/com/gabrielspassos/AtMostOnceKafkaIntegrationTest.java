package com.gabrielspassos;

import com.gabrielspassos.consumer.TestConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = "app.mode=AT_MOST_ONCE")
class AtMostOnceKafkaIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    void testAtMostOnce() throws Exception {
        kafkaTemplate.send("delivery-at-most-once-test", "error-induction");

        Thread.sleep(5000);

        assertEquals(1, testConsumer.getProcessedAtMostOnce().size());
    }
}
