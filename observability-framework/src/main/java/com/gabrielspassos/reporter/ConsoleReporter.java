package com.gabrielspassos.reporter;

import com.gabrielspassos.core.LatencyRegistry;

public class ConsoleReporter implements MetricReporter {

    @Override
    public boolean report(LatencyRegistry latencyRegistry) {
        latencyRegistry.getMetrics().forEach((metricName, metric) -> {
            var snapshot = latencyRegistry.snapshot(metricName);

            IO.println("Metric: " + snapshot.name());
            IO.println("Count: " + snapshot.count());
            IO.println("Avg(ms): " + snapshot.avgLatencyInMillis());
            IO.println("Max(ms): " + snapshot.maxLatencyInMillis());
            IO.println("-----");
        });

        return true;
    }

}
