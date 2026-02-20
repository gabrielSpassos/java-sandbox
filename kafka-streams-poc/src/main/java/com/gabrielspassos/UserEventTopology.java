package com.gabrielspassos;

import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

public class UserEventTopology {

    public static final String INPUT_TOPIC = "input-events";
    private static final String STORAGE = "user-count-store";
    public static final String OUTPUT_TOPIC = "user-event-counts";

    public static Topology build() {
        StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> events = builder.stream(INPUT_TOPIC);

        KTable<String, Long> counts = events
                .map((_, value) -> {
                    String user = value.split(",")[0];
                    return KeyValue.pair(user, value);
                })
                .groupByKey()
                .count(Materialized.as(STORAGE));

        counts.toStream()
                .mapValues(String::valueOf)
                .to(OUTPUT_TOPIC);

        return builder.build();
    }
}
