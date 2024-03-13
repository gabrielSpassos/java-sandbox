package com.gabrielspassos.service;

import com.gabrielspassos.model.Message;
import com.gabrielspassos.queue.QueueManager;

import java.util.UUID;

public class PublisherService {

    private static PublisherService instance;
    private Long counter;

    public static synchronized PublisherService getPublisherService() {
        if (null == instance) {
            instance = new PublisherService();
        }
        return instance;
    }

    private PublisherService() {
        this.counter = 0L;
    }

    public void publishMessage(Integer quantity, QueueManager queue) {
        for (int i = 0; i < quantity; i++) {
            Message message = new Message(UUID.randomUUID().toString(), counter++);
            queue.enqueue(message);
            System.out.println("Published message " + message + " to inbound queue");
        }
    }
}
