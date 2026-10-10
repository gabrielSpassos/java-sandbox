package com.gabrielspassos.client;

import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExchangeClient {

    private static final Logger logger = LoggerFactory.getLogger(ExchangeClient.class);

    private final RestClient restClient;

    public ExchangeClient(@Qualifier("exchangeRestClientBuilder") RestClient.Builder restClientBuilder,
                          @Value("${exchange.api.url}") String url) {
        this.restClient = restClientBuilder.baseUrl(url).build();
    }

    public UsdResponse getUsdToBrl() {
        try {
            UsdResponse response = restClient.get()
                    .uri("/npm/@fawazahmed0/currency-api@latest/v1/currencies/usd.json")
                    .retrieve()
                    .body(UsdResponse.class);

            if (response == null || response.usd() == null) {
                throw new NotFoundException("Could not retrieve exchange rate", "NOT_FOUND_EXCHANGE");
            }

            return response;
        } catch (Exception e) {
            logger.error("Error to fetch usd exchange", e);
            throw e;
        }
    }

}
