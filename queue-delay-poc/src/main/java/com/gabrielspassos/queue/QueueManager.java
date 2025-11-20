package com.gabrielspassos.queue;

import com.gabrielspassos.task.Task;

import java.util.concurrent.*;

public class QueueManager {

    private static final Integer QUEUE_PROCESSING_QTD = 1;
    private static final Long TTL_TIME_IN_MINUTES = 5L;

    private ExecutorService executor = new ThreadPoolExecutor(
            QUEUE_PROCESSING_QTD,                      // corePoolSize
            QUEUE_PROCESSING_QTD,                      // maxPoolSize
            TTL_TIME_IN_MINUTES,                       // keepAliveTime
            TimeUnit.MINUTES,                          // unit
            new LinkedBlockingQueue<>(100),    // capacity
            new ThreadPoolExecutor.AbortPolicy()       // action to take when the queue is full
    );

    public <T> boolean executeTask(Task<T> task, T input) {
        try {
            Future<Boolean> future = executor.submit(() -> task.execute(input));
            return future.get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
