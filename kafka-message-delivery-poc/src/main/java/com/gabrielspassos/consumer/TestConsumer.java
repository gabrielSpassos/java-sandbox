package com.gabrielspassos.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TestConsumer {

    private final List<String> processed = new CopyOnWriteArrayList<>();

    public List<String> getProcessed() {
        return processed;
    }

    @KafkaListener(topics = "delivery-test", containerFactory = "kafkaListenerFactory")
    public void consume(String message, Acknowledgment ack) {
        IO.println("Processing: " + message);

        if (message.equals("msg-5") && processed.size() < 5) {
            throw new RuntimeException("Simulated crash");
        }

        processed.add(message);

        ack.acknowledge();
    }

}
