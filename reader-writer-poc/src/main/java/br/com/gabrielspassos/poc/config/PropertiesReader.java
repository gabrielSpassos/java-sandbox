package br.com.gabrielspassos.poc.config;

import java.io.InputStream;
import java.util.Objects;
import java.util.Properties;

public class PropertiesReader {

    private Properties properties;
    private static PropertiesReader propertiesReader;
    private static final String PROPERTIES_FILE_NAME = "application.properties";

    private PropertiesReader() {
        properties = new Properties();
        loadProperties();
    }

    public static synchronized PropertiesReader getInstance() {
        if(Objects.isNull(propertiesReader)) {
            propertiesReader = new PropertiesReader();
        }

        return propertiesReader;
    }

    public Properties getApplicationProperties(){
        return properties;
    }

    private void loadProperties() {
        try {
            InputStream input = getClass().getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
            properties.load(input);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
