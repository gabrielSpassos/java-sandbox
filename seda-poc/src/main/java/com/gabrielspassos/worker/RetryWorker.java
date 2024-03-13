package com.gabrielspassos.worker;

import com.gabrielspassos.model.Message;
import com.gabrielspassos.queue.QueueManager;

public class RetryWorker implements Worker {

    private final QueueManager inputQueue;
    private final QueueManager outputQueue;
    private static final int RETRY_MAX_COUNT = 3;
    private static final int RETRY_BACKOFF_MULTIPLIER = 2;

    public RetryWorker(QueueManager inputQueue, QueueManager outputQueue) {
        this.inputQueue = inputQueue;
        this.outputQueue = outputQueue;
    }

    @Override
    public void run() {
        while (true) {
            Message message = inputQueue.dequeue();
            if (null != message) {
                System.out.println("Retry processing message " + message);
                for (int retryCount = 1; retryCount <= RETRY_MAX_COUNT; retryCount++) {
                    if (isSuccessfulMessage(message)) {
                        System.out.println("Successfully processed message on retry queue" + message);
                        break;
                    }

                    if (retryCount == RETRY_MAX_COUNT) {
                        System.out.println("Failed to process retry message " + message + " sending to DLQ");
                        outputQueue.enqueue(message);
                    } else {
                        long sleep =  retryCount * RETRY_BACKOFF_MULTIPLIER * getRandomNumber(50, 500);
                        try {
                            Thread.sleep(sleep);
                        } catch (InterruptedException e) {
                            System.out.println("Error to sleep " + e);
                        }
                    }
                }
            }
        }
    }

    private boolean isSuccessfulMessage(Message message) {
        long randomNumber = getRandomNumber(message.getContent(), message.getContent() * 1000);
        return randomNumber % 2 == 0;
    }

    private long getRandomNumber(long min, long max) {
        return (long) ((Math.random() * (max - min)) + min);
    }
}
