package com.gabrielspassos;

import com.gabrielspassos.consumer.TestConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestPropertySource(properties = "app.mode=EXACTLY_ONCE")
class ExactlyKafkaIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TestConsumer testConsumer;

    @Test
    void testExactlyOnce() throws Exception {
        kafkaTemplate.send("delivery-exactly-once-input", "msg-1");

        Thread.sleep(5000);

        List<String> exactlyOnce = testConsumer.getProcessedExactlyOnce();

        assertEquals(1, exactlyOnce.size());
    }
}
