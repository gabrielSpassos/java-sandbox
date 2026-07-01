package com.gabrielspassos.client;

import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.exception.NotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class ExchangeClient {

    private final RestClient restClient;

    public ExchangeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    public UsdResponse getUsdToBrl() {
        UsdResponse response = restClient.get()
                .uri("/npm/@fawazahmed0/currency-api@latest/v1/currencies/usd.json")
                .retrieve()
                .body(UsdResponse.class);

        if (response == null || response.usd() == null) {
            throw new NotFoundException("Could not retrieve exchange rate", "NOT_FOUND_EXCHANGE");
        }

        return response;
    }
}
