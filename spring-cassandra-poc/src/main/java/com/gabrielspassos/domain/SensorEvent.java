package com.gabrielspassos.domain;

import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;

import java.time.Instant;

@Table("sensor_events")
public record SensorEvent(

        @PrimaryKeyColumn(
                name = "sensor_id",
                type = PrimaryKeyType.PARTITIONED,
                ordinal = 0)
        String sensorId,

        @PrimaryKeyColumn(
                type = PrimaryKeyType.CLUSTERED,
                ordinal = 1,
                ordering = Ordering.DESCENDING)
        Instant timestamp,

        Double value
) {}
