package org.gabrielspassos.internal;

import org.gabrielspassos.external.Task;

import java.time.ZonedDateTime;

public class GabrielThread extends Thread {

    private final ThreadPool threadPool;

    public GabrielThread(String nameComplement, ThreadPool threadPool) {
        super("Gabriel-Thread-" + nameComplement);
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        while(!isInterrupted()) {
            Task task = threadPool.getFirstTaskOnQueue();

            if (task != null) {
                System.out.printf("Thread[%s] - Started at %s%n", this.getName(), ZonedDateTime.now());
                task.execute();
                System.out.printf("Thread[%s] - Finished at %s%n", this.getName(), ZonedDateTime.now());
            }

        }

        threadPool.removeThread(this);
    }

    public boolean stopThread() {
        interrupt();
        return isInterrupted();
    }

}
