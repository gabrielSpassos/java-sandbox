package com.gabrielspassos.core;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

public class LatencyRegistry {

    private final ConcurrentHashMap<String, MutableMetric> metrics = new ConcurrentHashMap<>();

    public boolean record(String metricName, long latency) {
        return metrics.computeIfAbsent(metricName, name -> new MutableMetric()).record(latency);
    }

    public LatencyMetric snapshot(String metricName) {
        var metric = metrics.get(metricName);

        if (null == metric) {
            return new LatencyMetric(metricName, 0, 0, 0);
        }

        return new LatencyMetric(metricName, metric.count.sum(), metric.totalLatency.sum(), metric.maxLatency);
    }

    public ConcurrentHashMap<String, MutableMetric> getMetrics() {
        return metrics;
    }

    public static class MutableMetric {

        private final LongAdder count = new LongAdder();
        private final LongAdder totalLatency = new LongAdder();
        private volatile long maxLatency = 0;

        synchronized boolean record(long latency) {
            count.increment();
            totalLatency.add(latency);

            if (latency > maxLatency) {
                maxLatency = latency;
            }

            return true;
        }
    }
}
