package com.gabrielspassos.core;

public class TimerSample {

    private final long startTime;

    public TimerSample(long startTime) {
        this.startTime = startTime;
    }

    public static TimerSample start() {
        return new TimerSample(System.nanoTime());
    }

    public long stop() {
        return System.nanoTime() - this.startTime;
    }
}
