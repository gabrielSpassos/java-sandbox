package com.gabrielspassos;

public class ConcurrencyPoC {

    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 100_000;

    static void main() throws InterruptedException {
        IO.println("Expected value: " + (THREADS * INCREMENTS_PER_THREAD));
        IO.println();

        runUnsafeCounter();
        runLockCounter();
        runAtomicCounter();
    }

    private static void runUnsafeCounter() throws InterruptedException {
        IO.println("Running UNSAFE counter...");

        Counter counter = new UnsafeCounter();
        Thread[] threads = createThreads(counter::increment);

        startAndJoin(threads);

        IO.println("Unsafe result: " + counter.getValue());
        IO.println();
    }

    private static void runLockCounter() throws InterruptedException {
        IO.println("Running LOCK counter...");

        Counter counter = new LockCounter();
        Thread[] threads = createThreads(counter::increment);

        startAndJoin(threads);

        IO.println("Lock result: " + counter.getValue());
        IO.println();
    }

    private static void runAtomicCounter() throws InterruptedException {
        IO.println("Running ATOMIC counter...");

        Counter counter = new AtomicCounter();
        Thread[] threads = createThreads(counter::increment);

        startAndJoin(threads);

        IO.println("Atomic result: " + counter.getValue());
        IO.println();
    }

    private static Thread[] createThreads(Runnable task) {
        Thread[] threads = new Thread[THREADS];

        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
                    task.run();
                }
            });
        }
        return threads;
    }

    private static void startAndJoin(Thread[] threads) throws InterruptedException {
        for (Thread t : threads) t.start();
        for (Thread t : threads) t.join();
    }
}
