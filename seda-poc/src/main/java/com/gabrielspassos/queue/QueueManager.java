package com.gabrielspassos.queue;

import com.gabrielspassos.model.Message;

import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueManager {

    private final String name;
    private final ConcurrentLinkedQueue<Message> queue;

    public QueueManager(String name) {
        this.name = name;
        this.queue = new ConcurrentLinkedQueue<>();
    }

    public Message enqueue(Message message) {
        queue.add(message);
        return message;
    }

    public Message dequeue() {
        return queue.poll();
    }
}
