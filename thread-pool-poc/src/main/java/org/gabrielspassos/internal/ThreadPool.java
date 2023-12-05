package org.gabrielspassos.internal;

import org.gabrielspassos.external.Task;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {

    private static final int MAX_POOL_SIZE = 5;

    private final ConcurrentLinkedQueue<Task> tasks;
    private final ConcurrentLinkedQueue<GabrielThread> threads;

    public ThreadPool() {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.threads = initializeThreads(MAX_POOL_SIZE);
    }

    public ThreadPool(Integer poolSize) {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.threads = initializeThreads(poolSize);
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    public boolean removeThread(GabrielThread thread) {
        return threads.remove(thread);
    }

    public boolean stopThreads() throws InterruptedException {
        while (!threads.isEmpty()) {
            if (tasks.isEmpty()) {
                for (GabrielThread thread : threads) {
                    thread.stopThread();
                }
            }

            Thread.sleep(1);
        }
        return true;
    }

    protected Task getFirstTaskOnQueue() {
        return tasks.poll();
    }

    private ConcurrentLinkedQueue<GabrielThread> initializeThreads(Integer poolSize) {
        final ConcurrentLinkedQueue<GabrielThread> threads = new ConcurrentLinkedQueue<>();
        for (int i = 1; i <= poolSize; i++) {
            GabrielThread thread = new GabrielThread(String.valueOf(i), this);
            threads.add(thread);
            thread.start();
        }
        return threads;
    }

}
