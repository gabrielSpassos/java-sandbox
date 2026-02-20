package com.gabrielspassos;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

import java.util.Properties;

public class Main {

    private static final String INPUT_TOPIC = "input-events";
    private static final String STORAGE = "user-count-store";
    private static final String OUTPUT_TOPIC = "user-event-counts";

    static void main() {
        IO.println("Kafka Streams POC!");

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-poc");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);

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

        KafkaStreams streams = new KafkaStreams(builder.build(), props);

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

        streams.start();
    }
}
