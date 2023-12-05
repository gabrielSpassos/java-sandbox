package org.gabrielspassos.internal;

import org.gabrielspassos.task.TaskWithoutDelay;
import org.junit.jupiter.api.Test;

public class TaskWithoutDelayPerformanceThreadPoolTest {

    @Test
    void shouldAddOneTaskToThreadPool() throws InterruptedException {
        testPerformanceBaseScenario(1);
    }

    @Test
    void shouldAddOneThousandTaskToThreadPool() throws InterruptedException {
        testPerformanceBaseScenario(1000);
    }

    @Test
    void shouldAddOneMillionTaskToThreadPool() throws InterruptedException {
        testPerformanceBaseScenario(1000000);
    }

    @Test
    void shouldAddFiveMillionsTaskToThreadPool() throws InterruptedException {
        testPerformanceBaseScenario(5000000);
    }

    @Test
    void shouldAddThirtyMillionsTaskToThreadPool() throws InterruptedException {
        testPerformanceBaseScenario(30000000);
    }

    private void testPerformanceBaseScenario(int iterations) throws InterruptedException {
        System.out.printf("Test Performance with %s iterations%n", iterations);
        long startWithoutCache = System.currentTimeMillis();
        ThreadPool threadPool = new ThreadPool(5);

        for (int i = 0; i < iterations ; i++) {
            threadPool.addTask(new TaskWithoutDelay());
        }

        threadPool.stopThreads();
        long finishWithoutCache = System.currentTimeMillis();
        System.out.printf("Test Performance with %s iterations took: %s milliseconds%n",
                iterations, finishWithoutCache - startWithoutCache);
    }
}
