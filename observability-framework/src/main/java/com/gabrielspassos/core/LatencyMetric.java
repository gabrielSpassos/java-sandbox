package com.gabrielspassos.core;

public record LatencyMetric(String name,
                            long count,
                            long totalLatencyInNanos,
                            long maxLatencyInNanos) {

    public double avgLatencyInMillis() {
        if (0 == count) {
            return 0;
        }

        return nanoToMilli(totalLatencyInNanos) / count;
    }

    public double maxLatencyInMillis() {
        return nanoToMilli(maxLatencyInNanos);
    }

    private double nanoToMilli(long nano) {
        return nano / 1_000_000.0;
    }
}
