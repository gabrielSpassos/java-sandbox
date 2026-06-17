package com.gabrielspassos.service;

import com.gabrielspassos.TestcontainersConfiguration;
import com.gabrielspassos.domain.SensorEvent;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.Instant;
import java.util.UUID;

@Import(TestcontainersConfiguration.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class SensorServiceIntegrationTest {

    @Autowired
    private SensorService sensorService;

    @BeforeAll
    void setup() {
        sensorService.deleteAll();
    }

    @Test
    void shouldTestSavePerformance() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100_000; i++) {
            var event = createEvent(i);
            sensorService.save(event);
        }
        long timeTaken = System.currentTimeMillis() - start;
        IO.println("Total time take: " + timeTaken);
    }

    private SensorEvent createEvent(int i) {
        return new SensorEvent(UUID.randomUUID().toString(), Instant.now(), 1.0 * i);
    }
}