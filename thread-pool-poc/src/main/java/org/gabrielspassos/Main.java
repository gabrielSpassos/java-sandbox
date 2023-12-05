package org.gabrielspassos;

import org.gabrielspassos.internal.ThreadPool;
import org.gabrielspassos.task.TaskWithDelay;
import org.gabrielspassos.task.TaskWithoutDelay;

public class Main {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool();
        threadPool.addTask(new TaskWithoutDelay());
        threadPool.addTask(new TaskWithoutDelay());
        threadPool.addTask(new TaskWithDelay());
    }
}