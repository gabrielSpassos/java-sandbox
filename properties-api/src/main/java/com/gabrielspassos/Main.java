package com.gabrielspassos;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    static void main() {
        IO.println("Properties API POC");

        // In memory
        Properties defaults = new Properties();
        defaults.setProperty("timeout", "30");

        Properties properties = new Properties(defaults);
        properties.setProperty("app.name", "properties-api");
        properties.setProperty("app.port", "8080");

        properties.forEach((key, value) -> {
            IO.println(key + " -> " + value);
        });

        System.out.println("timeout -> " + properties.getProperty("timeout"));

        // From classpath file
        Properties classPathProperties = new Properties(defaults);
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("application.properties")) {
            classPathProperties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        classPathProperties.forEach((key, value) -> {
            IO.println(key + " -> " + value);
        });
    }
}
