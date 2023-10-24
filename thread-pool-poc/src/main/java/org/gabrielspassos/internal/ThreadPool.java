package org.gabrielspassos.internal;

import org.gabrielspassos.external.Task;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ThreadPool {

    private final ConcurrentLinkedQueue<Task> tasks;
    private final ConcurrentLinkedQueue<Thread> threads;

    public ThreadPool() {
        this.tasks = new ConcurrentLinkedQueue<>();
        this.threads = Stream.generate(Thread::new)
                .limit(5)
                .collect(Collectors.toCollection(ConcurrentLinkedQueue::new));
    }

    public void addTask(Task task) {
        threads.stream()
                .filter(thread -> Thread.State.NEW.equals(thread.getState())
                        || Thread.State.TERMINATED.equals(thread.getState()))
                .map(thread -> {
                    threads.remove(thread);
                    Runnable runnable = task::execute;
                    Thread newThread = new Thread(runnable);
                    threads.add(newThread);
                    newThread.run();
                    return task;
                })
                .findFirst()
                .orElseGet(() -> {
                    tasks.add(task);
                    return task;
                });
    }

    protected ConcurrentLinkedQueue<Task> getTasks() {
        return tasks;
    }

    protected ConcurrentLinkedQueue<Thread> getThreads() {
        return threads;
    }

}
