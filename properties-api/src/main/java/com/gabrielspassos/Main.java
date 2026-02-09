package com.gabrielspassos;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class Main {
    static void main() {
        IO.println("Properties API POC");

        // In memory
        IO.println("In Memory Properties");
        Properties defaults = new Properties();
        defaults.setProperty("timeout", "30");

        Properties properties = new Properties(defaults);
        properties.setProperty("app.name", "properties-api");
        properties.setProperty("app.port", "8081");

        properties.forEach((key, value) -> {
            IO.println(key + " -> " + value);
        });

        System.out.println("timeout -> " + properties.getProperty("timeout"));
        System.out.println("withoutDefault -> " + properties.getProperty("withoutDefault"));
        System.out.println("withDefault -> " + properties.getProperty("withDefault", "withDefault"));

        // From classpath file
        IO.println("\n\nRead Properties");
        Properties classPathProperties = new Properties();
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

        IO.println("\n\nRead XML Properties");
        Properties xmlClassPathProperties = new Properties();
        try (InputStream inputStream = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream("config.xml")) {
            xmlClassPathProperties.loadFromXML(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        xmlClassPathProperties.forEach((key, value) -> {
            IO.println(key + " -> " + value);
        });

        // Store
        try (FileWriter writer = new FileWriter("src/main/resources/config.properties")) {
            properties.store(writer, "Configurations");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream output = new FileOutputStream("src/main/resources/config.xml")) {
            properties.storeToXML(output, "Configurations", StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // System properties
        IO.println("\n\nSystem Properties");
        System.setProperty("is.poc", "true");
        System.getProperties().forEach((key, value) -> {
            IO.println(key + " -> " + value);
        });

    }
}
