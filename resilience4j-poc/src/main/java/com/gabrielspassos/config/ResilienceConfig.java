package com.gabrielspassos.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.retry.RetryConfig;

import java.time.Duration;

public class ResilienceConfig {

    public static CircuitBreaker circuitBreaker() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(5))
                .slidingWindowSize(10)
                .build();

        CircuitBreaker circuitBreaker = CircuitBreaker.of("externalClient", config);
        circuitBreaker.getEventPublisher()
                .onStateTransition(event -> IO.println("State change: " + event));
        return circuitBreaker;
    }

    public static Retry retry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(3)
                .waitDuration(Duration.ofMillis(500))
                .build();

        return Retry.of("externalClient", config);
    }

}