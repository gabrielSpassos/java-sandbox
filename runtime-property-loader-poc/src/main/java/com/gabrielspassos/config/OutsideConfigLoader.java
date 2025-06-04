package com.gabrielspassos.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
public class OutsideConfigLoader {

    @Bean
    public PropertySourcesPlaceholderConfigurer properties(){
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        var resource = new FileSystemResource("conf/outside-config.properties");
        propertySourcesPlaceholderConfigurer.setLocations( resource );
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders( true );
        return propertySourcesPlaceholderConfigurer;
    }
}
