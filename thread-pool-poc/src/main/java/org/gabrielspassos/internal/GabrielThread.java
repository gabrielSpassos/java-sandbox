package org.gabrielspassos.internal;

import org.gabrielspassos.external.Task;

import java.time.ZonedDateTime;
import java.util.Optional;

public class GabrielThread extends Thread {

    private final ThreadPool threadPool;

    public GabrielThread(String nameComplement, ThreadPool threadPool) {
        super("Gabriel-Thread-" + nameComplement);
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        //todo: how to interrupt
        while(!isInterrupted()) {
            execute();
        }

//        //todo: how to check if thread can run the code?
//        while (Thread.State.NEW.equals(this.getState()) || Thread.State.TERMINATED.equals(this.getState())) {
//            execute();
//        }
    }

    //todo: move to run method once it is finished
    private void execute() {
        Optional<Task> task = threadPool.getFirstTaskOnQueue();

        if (!task.isPresent()) {
            return; //todo: should do something?
        }

        System.out.printf("Thread[%s] - Started at %s%n", this.getName(), ZonedDateTime.now());
        Object execute = task.get().execute();
        //todo why is not print this?
        System.out.printf("Thread[%s] - Execute output%s%n", this.getName(), execute);
        System.out.printf("Thread[%s] - Finished at %s%n", this.getName(), ZonedDateTime.now());
    }
}
