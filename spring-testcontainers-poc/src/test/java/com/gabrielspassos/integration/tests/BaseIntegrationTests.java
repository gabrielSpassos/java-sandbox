package com.gabrielspassos.integration.tests;

import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.lang.reflect.Field;
import java.util.Map;

@Testcontainers
public class BaseIntegrationTests {

    @ClassRule
    public static MySQLContainer<?> mysql = new MySQLContainer<>("mysql:latest")
            .withInitScript("schema.sql");

    @BeforeAll
    static void startDb() {
        System.out.println("Setting up podman env");
        Map<String, String> newEnv = getModifiableEnvironmentMap();
        newEnv.put("DOCKER_HOST", "unix:///var/folders/dq/5vhbc0d51vn01dy58zsr341xpwqf1d/T/podman/podman-machine-default-api.sock");
        newEnv.put("TESTCONTAINERS_RYUK_DISABLED", "true");

        mysql.start();
    }

    @DynamicPropertySource
    static void registerPgProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", () -> mysql.getJdbcUrl());
        registry.add("spring.datasource.username", () -> mysql.getUsername());
        registry.add("spring.datasource.password", () -> mysql.getPassword());
    }

// With this on the second test we don't have a DB
//    @AfterAll
//    static void destroyDB() {
//        mysql.stop();
//    }

    private static Map<String, String> getModifiableEnvironmentMap() {
        try {
            Map<String, String> unmodifiableEnv = System.getenv();
            Class<?> cl = unmodifiableEnv.getClass();
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Map<String, String> modifiableEnv = (Map<String, String>) field.get(unmodifiableEnv);
            return modifiableEnv;
        } catch (Exception e) {
            throw new RuntimeException("Unable to access writable environment variable map.");
        }
    }

}
