package com.gabrielspassos.reporter;

import com.gabrielspassos.core.LatencyRegistry;

public interface MetricReporter {

    boolean report(LatencyRegistry latencyRegistry);

}
