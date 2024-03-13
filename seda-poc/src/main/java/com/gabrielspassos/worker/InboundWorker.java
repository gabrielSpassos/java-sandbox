package com.gabrielspassos.worker;

import com.gabrielspassos.model.Message;
import com.gabrielspassos.queue.QueueManager;

public class InboundWorker implements Worker {

    private final QueueManager inputQueue;
    private final QueueManager outputQueue;

    public InboundWorker(QueueManager inputQueue, QueueManager outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message = inputQueue.dequeue();
            if (null != message) {
                System.out.println("Inbound processing message " + message);
                if (isSuccessfulMessage(message)) {
                    System.out.println("Successfully processed message " + message);
                } else {
                    System.out.println("Failed to processed message " + message + " sending to retry");
                    outputQueue.enqueue(message);
                }
            }
        }
    }

    private boolean isSuccessfulMessage(Message message) {
        return message.getContent() % 2 == 0;
    }

}
