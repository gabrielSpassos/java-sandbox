package org.gabrielspassos.internal;

import org.gabrielspassos.task.TaskA;
import org.gabrielspassos.task.TaskB;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ThreadPoolTest {

    @Test
    void shouldAddTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskA());

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

    @Test
    void shouldAddTwoTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        threadPool.addTask(new TaskA());
        threadPool.addTask(new TaskA());

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

    @Test
    void shouldAddMultipleTaskToThreadPool() {
        ThreadPool threadPool = new ThreadPool();

        for (int i = 0; i < 10 ; i++) {
            threadPool.addTask(new TaskB());
            threadPool.addTask(new TaskB());
            threadPool.addTask(new TaskB());
        }

        assertEquals(5, threadPool.getThreads().size());
        assertEquals(0, threadPool.getTasks().size());
    }

}