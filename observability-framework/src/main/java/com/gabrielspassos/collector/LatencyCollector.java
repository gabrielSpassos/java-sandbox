package com.gabrielspassos.collector;

import com.gabrielspassos.core.LatencyRegistry;
import com.gabrielspassos.core.TimerSample;

import java.util.function.Supplier;

public class LatencyCollector {

    private final LatencyRegistry latencyRegistry;

    public LatencyCollector(LatencyRegistry latencyRegistry) {
        this.latencyRegistry = latencyRegistry;
    }

    public <T> T measure(String metricName, Supplier<T> supplier) {
        var timer = TimerSample.start();

        try {
            return supplier.get();
        } finally {
            var latency = timer.stop();
            latencyRegistry.record(metricName, latency);
        }
    }

    public void measure(String metricName, Runnable runnable) {
        var timer = TimerSample.start();

        try {
            runnable.run();
        } finally {
            var latency = timer.stop();
            latencyRegistry.record(metricName, latency);
        }
    }
}
