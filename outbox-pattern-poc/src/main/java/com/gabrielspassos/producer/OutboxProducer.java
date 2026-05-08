package com.gabrielspassos.producer;

import com.gabrielspassos.repository.OutboxRepository;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class OutboxProducer {

    private final OutboxRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;

    public OutboxProducer(OutboxRepository repository, KafkaTemplate<String, String> kafkaTemplate) {
        this.repository = repository;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Transactional
    public void publish() {
        var events = repository.findByProcessedIsFalse();

        for (var event : events) {
            kafkaTemplate.send("orders-topic", event.getAggregateId().toString(), event.getPayload());
            event.markProcessed();
            repository.save(event);
        }
    }

}
