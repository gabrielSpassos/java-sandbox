package com.gabrielspassos.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ExchangeClientConfig {

    @Bean
    RestClient currencyRestClient(RestClient.Builder builder) {
        return builder
                .baseUrl("https://cdn.jsdelivr.net")
                .build();
    }

}