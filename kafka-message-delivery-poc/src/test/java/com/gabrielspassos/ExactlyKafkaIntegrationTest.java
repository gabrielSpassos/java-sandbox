package com.gabrielspassos;

import com.gabrielspassos.consumer.OutputConsumer;
import com.gabrielspassos.consumer.TestConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestPropertySource(properties = "app.mode=EXACTLY_ONCE")
class ExactlyKafkaIntegrationTest extends BaseIntegrationTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private TestConsumer testConsumer;

    @Autowired
    private OutputConsumer outputConsumer;

    @Test
    void testExactlyOnce() throws Exception {
        kafkaTemplate.send("delivery-exactly-once-input", "msg-1");

        Thread.sleep(5000);

        List<String> output = outputConsumer.getProcessed();

        long count = output.stream()
                .filter(m -> m.equals("msg-1"))
                .count();

        assertEquals(1, count);
    }
}
