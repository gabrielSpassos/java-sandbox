package org.gabrielspassos.internal;

import org.gabrielspassos.external.Task;

import java.util.Optional;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ThreadPool {

    private static final int MAX_POOL_SIZE = 5;

    private final ConcurrentLinkedQueue<Task> tasks;
    private final ConcurrentLinkedQueue<GabrielThread> threads;

    public ThreadPool() {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.threads = initializeThreads();
    }

    public Task addTask(Task task) {
        tasks.add(task);
        return task;
    }

    protected Optional<Task> getFirstTaskOnQueue() {
        return Optional.ofNullable(tasks.poll());
    }

    protected ConcurrentLinkedQueue<Task> getTasks() {
        return tasks;
    }

    protected ConcurrentLinkedQueue<GabrielThread> getThreads() {
        return threads;
    }

    private ConcurrentLinkedQueue<GabrielThread> initializeThreads() {
        final ConcurrentLinkedQueue<GabrielThread> threads = new ConcurrentLinkedQueue<>();
        for (int i = 1; i <= MAX_POOL_SIZE; i++) {
            GabrielThread thread = new GabrielThread(String.valueOf(i), this);
            threads.add(thread);
            thread.start(); //todo: why this works with start and not with run?
        }
        return threads;
    }

}
