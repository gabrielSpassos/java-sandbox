package com.gabrielspassos.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class OutputConsumer {

    private final List<String> processed = new CopyOnWriteArrayList<>();

    public List<String> getProcessed() {
        return processed;
    }

    @KafkaListener(
            topics = "delivery-exactly-once-output",
            groupId = "output-consumer-group",
            containerFactory = "outputKafkaListenerFactory"
    )
    public void consume(String message) {
        System.out.println("OUTPUT RECEIVED: " + message);
        processed.add(message);
    }
}
