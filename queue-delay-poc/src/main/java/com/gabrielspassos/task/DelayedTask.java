package com.gabrielspassos.task;

import java.util.concurrent.TimeoutException;
import java.util.random.RandomGenerator;

public interface DelayedTask<T> extends Task<T> {

    RandomGenerator RANDOM_GENERATOR = RandomGenerator.getDefault();

    long maxDelayInMillis();

    long minDelayInMillis();

    boolean executeWithDelay(T input);

    @Override
    default boolean execute(T input) {
        try {
            var delay = getDelayInMillis();
            IO.println("Delaying execution for " + delay + " milliseconds.");
            Thread.sleep(delay);
            return executeWithDelay(input);
        } catch (InterruptedException e) {
            IO.println("Interrupted task ");
            e.printStackTrace();
            Thread.currentThread().interrupt();
            return false;
        }
    }

    private long getDelayInMillis() {
        long min = minDelayInMillis();
        long max = maxDelayInMillis();

        if (min >= max) {
            return min;
        }

        return RANDOM_GENERATOR.nextLong(min, max);
    }
}
