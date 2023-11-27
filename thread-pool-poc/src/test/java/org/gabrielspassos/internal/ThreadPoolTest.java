package org.gabrielspassos.internal;

import org.gabrielspassos.task.TaskWithDelay;
import org.gabrielspassos.task.TaskWithoutDelay;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadPoolTest {

    @Test
    void shouldAddTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskWithoutDelay());

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

    @Test
    void shouldAddTwoTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskWithDelay());
        threadPool.addTask(new TaskWithDelay());

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

    @Test
    void shouldAddMultipleTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        for (int i = 0; i < 1000 ; i++) {
            threadPool.addTask(new TaskWithDelay());
        }

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

}