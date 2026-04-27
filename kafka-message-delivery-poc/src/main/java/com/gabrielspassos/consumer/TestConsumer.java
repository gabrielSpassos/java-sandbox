package com.gabrielspassos.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class TestConsumer {

    private final List<String> processedAtLeastOnce = new CopyOnWriteArrayList<>();
    private final List<String> processedAtMostOnce = new CopyOnWriteArrayList<>();
    private final List<String> processedExactlyOnce = new CopyOnWriteArrayList<>();

    public List<String> getProcessedAtLeastOnce() {
        return processedAtLeastOnce;
    }

    public List<String> getProcessedAtMostOnce() {
        return processedAtMostOnce;
    }

    public List<String> getProcessedExactlyOnce() {
        return processedExactlyOnce;
    }

    @KafkaListener(topics = "delivery-at-least-once-test", containerFactory = "kafkaListenerFactory")
    public void consumeAtLeastOnce(String message, Acknowledgment ack) {
        IO.println("Processing: " + message);
        processedAtLeastOnce.add(message);

        if (message.equals("error-induction") && processedAtLeastOnce.size() < 5) {
            throw new RuntimeException("Simulated crash");
        }

        ack.acknowledge();
    }

    @KafkaListener(topics = "delivery-at-most-once-test", containerFactory = "kafkaListenerFactory")
    public void consumeAtMostOnce(String message, Acknowledgment ack) {
        ack.acknowledge();
        IO.println("Processing: " + message);
        processedAtMostOnce.add(message);

        if (message.equals("error-induction") && processedAtMostOnce.size() < 5) {
            throw new RuntimeException("Simulated crash");
        }
    }

    @KafkaListener(topics = "delivery-exactly-once-input", containerFactory = "kafkaListenerFactory")
    @Transactional
    public void consumeExactlyOnce(
            ConsumerRecord<String, String> record,
            KafkaTemplate<String, String> kafkaTemplate
    ) {
        System.out.println("Processing offset=" + record.offset());
        processedExactlyOnce.add(record.value());

        if (record.value().equals("error-induction")) {
            throw new RuntimeException("fail before commit");
        }
    }

}
