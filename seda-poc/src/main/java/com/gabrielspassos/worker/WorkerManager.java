package com.gabrielspassos.worker;

import com.gabrielspassos.queue.QueueManager;
import com.gabrielspassos.service.PublisherService;

import java.util.ArrayList;
import java.util.List;

public class WorkerManager {

    private static WorkerManager instance;
    private final PublisherService publisherService;

    public static synchronized WorkerManager getWorkerManager() {
        if (null == instance) {
            instance = new WorkerManager();
        }
        return instance;
    }

    private WorkerManager() {
        this.publisherService = PublisherService.getPublisherService();
    }

    public void execute() {
        final QueueManager inboundQueue = new QueueManager("inbound-queue");
        final QueueManager retryQueue = new QueueManager("retry-queue");
        final QueueManager dlqQueue = new QueueManager("dlq-queue");

        final Worker inboundWorker = new InboundWorker(inboundQueue, retryQueue);
        final Worker retryWorker = new RetryWorker(retryQueue, dlqQueue);

        List<Thread> threads = new ArrayList<>();
        threads.add(new Thread(inboundWorker::run));
        threads.add(new Thread(retryWorker::run));

        publisherService.publishMessage(100, inboundQueue);

        for (Thread thread : threads) {
            thread.start();
        }
    }
}
