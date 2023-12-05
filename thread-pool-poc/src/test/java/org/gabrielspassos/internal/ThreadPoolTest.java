package org.gabrielspassos.internal;

import org.gabrielspassos.task.TaskWithDelay;
import org.gabrielspassos.task.TaskWithoutDelay;
import org.junit.jupiter.api.Test;

class ThreadPoolTest {

    @Test
    void shouldAddTaskToThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskWithoutDelay());

        threadPool.stopThreads();
    }

    @Test
    void shouldAddTwoTaskToThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskWithDelay());
        threadPool.addTask(new TaskWithDelay());

        threadPool.stopThreads();
    }

}