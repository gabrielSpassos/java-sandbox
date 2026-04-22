package com.gabrielspassos.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Bean
    public ProducerFactory<String, String> producerFactory(@Value("${app.mode}") String mode,
                                                           @Value("${spring.kafka.bootstrap-servers}") String servers) {
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, servers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);

        switch (mode) {
            case "AT_MOST_ONCE" -> {
                props.put(ProducerConfig.ACKS_CONFIG, "0");
                props.put(ProducerConfig.RETRIES_CONFIG, 0);
            }
            case "AT_LEAST_ONCE" -> {
                props.put(ProducerConfig.ACKS_CONFIG, "all");
                props.put(ProducerConfig.RETRIES_CONFIG, Integer.MAX_VALUE);
            }
            case "EXACTLY_ONCE" -> {
                props.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
                props.put(ProducerConfig.ACKS_CONFIG, "all");
                props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "tx-id");
            }
        }

        return new DefaultKafkaProducerFactory<>(props);
    }

    @Bean
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> pf) {
        return new KafkaTemplate<>(pf);
    }

}
