package com.gabrielspassos.client;

import com.gabrielspassos.client.response.UsdRates;
import com.gabrielspassos.client.response.UsdResponse;
import com.gabrielspassos.exception.NotFoundException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.math.BigDecimal;

@Component
public class ExchangeClient {

    private final RestClient restClient;

    public ExchangeClient(RestClient restClient) {
        this.restClient = restClient;
    }

    @Retry(name = "currency-api")
    @CircuitBreaker(name = "currency-api", fallbackMethod = "fallbackRate")
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

    public UsdResponse fallbackRate(Throwable t) {
        IO.println("Fallback executed: " + t.getMessage());

        return new UsdResponse("2026-06-30", new UsdRates(new BigDecimal("5.18")));
    }
}
