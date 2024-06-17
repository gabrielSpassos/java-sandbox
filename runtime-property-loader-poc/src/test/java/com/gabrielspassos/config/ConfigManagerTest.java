package com.gabrielspassos.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigManagerTest {

    @Autowired
    private ConfigManager configManager;

    @Test
    void shouldGetValueFromKeyOne() {
        assertEquals("value1", configManager.getKeyOne());
    }

    @Test
    void shouldGetValueFromKeyTwo() {
        assertEquals("defaultValueTwo", configManager.getKeyTwo());
    }

    @Test
    void shouldGetValueFromKeyThree() {
        assertEquals("value3", configManager.getKeyThree());
    }

    @Test
    void shouldGetValueFromKeyFour() {
        assertEquals("value4", configManager.getKeyFour());
    }

    @Test
    void shouldGetValueFromKeyFive() {
        assertEquals("value5", configManager.getKeyFive());
    }

    @Test
    void shouldGetValueFromKeySix() {
        assertEquals("value6", configManager.getKeySix());
    }

    @Test
    void shouldGetConfigs() {
        List<String> expectedConfigs = Arrays.asList("value1", "defaultValueTwo", "value3", "value4", "value5", "value6");

        assertEquals(expectedConfigs, configManager.getConfigs());
    }

}