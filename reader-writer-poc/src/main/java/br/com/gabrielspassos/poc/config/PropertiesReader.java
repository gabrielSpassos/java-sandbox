package br.com.gabrielspassos.poc.config;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;
    private static PropertiesReader propertiesReader;
    private static final Object lock = new Object();
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    private PropertiesReader() {
        properties = new Properties();
        loadProperties();
    }

    public static PropertiesReader getInstance() {
        synchronized (lock){
            if(Objects.isNull(propertiesReader)) {
                propertiesReader = new PropertiesReader();
            }
        }
        return propertiesReader;

    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    private void loadProperties() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            properties.load(input);
        } catch (Exception e) {
            throw new IllegalArgumentException("There isn't a properties file");
        }
    }
}
