package com.gabrielspassos.scheduler;

import com.gabrielspassos.producer.OutboxProducer;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboxScheduler {

    private final OutboxProducer producer;

    public OutboxScheduler(OutboxProducer producer) {
        this.producer = producer;
    }

    @Scheduled(fixedDelay = 2000)
    public void run() {
        producer.publish();
    }
}
