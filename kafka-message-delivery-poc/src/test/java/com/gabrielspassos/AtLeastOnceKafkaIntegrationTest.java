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
        kafkaTemplate.send("delivery-test", "msg-5");

        Thread.sleep(5000);

        long count = testConsumer.getProcessed().stream()
                .filter(m -> m.equals("msg-5"))
                .count();

        assertTrue(count >= 2);
    }
}