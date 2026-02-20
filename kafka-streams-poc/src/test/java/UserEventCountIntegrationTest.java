import com.gabrielspassos.UserEventTopology;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.testcontainers.kafka.KafkaContainer;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import static com.gabrielspassos.UserEventTopology.INPUT_TOPIC;
import static com.gabrielspassos.UserEventTopology.OUTPUT_TOPIC;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserEventCountIntegrationTest {

    private KafkaContainer kafka;

    @BeforeAll
    public void setup() {
        var dockerImage = DockerImageName
                .parse("confluentinc/cp-kafka:latest")
                .asCompatibleSubstituteFor("apache/kafka");
        this.kafka = new KafkaContainer(dockerImage);
        this.kafka.start();
    }

    @AfterAll
    public void cleanUp() {
        this.kafka.stop();
    }

    @Test
    void shouldCountUserEvents() {
        String bootstrap = kafka.getBootstrapServers();

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-poc");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        props.put(StreamsConfig.STATE_DIR_CONFIG, "target/kafka-streams");
        props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);


        KafkaStreams streams = new KafkaStreams(UserEventTopology.build(), props);
        streams.start();

        Properties producerProps = new Properties();
        producerProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        Producer<String, String> producer = new KafkaProducer<>(producerProps);
        producer.send(new ProducerRecord<>(INPUT_TOPIC, null, "user1,login"));
        producer.send(new ProducerRecord<>(INPUT_TOPIC, null, "user2,login"));
        producer.send(new ProducerRecord<>(INPUT_TOPIC, null, "user1,logout"));
        producer.send(new ProducerRecord<>(INPUT_TOPIC, null, "user1,login"));
        producer.flush();

        Properties consumerProps = new Properties();
        consumerProps.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrap);
        consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group");
        consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);

        Consumer<String, String> consumer = new KafkaConsumer<>(consumerProps);
        consumer.subscribe(List.of(OUTPUT_TOPIC));

        Map<String, String> results = new HashMap<>();

        long timeout = System.currentTimeMillis() + 10_000;

        while (System.currentTimeMillis() < timeout && results.size() < 2) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(200));

            for (ConsumerRecord<String, String> r : records) {
                results.put(r.key(), r.value());
            }
        }

        streams.close();

        assertEquals("3", results.get("user1"));
        assertEquals("1", results.get("user2"));
    }
}
