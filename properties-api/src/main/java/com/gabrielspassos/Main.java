package com.gabrielspassos;

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
    }
}
