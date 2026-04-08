package com.gabrielspassos.service;

import com.gabrielspassos.client.ExternalClient;
import com.gabrielspassos.config.ResilienceConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import io.github.resilience4j.ratelimiter.RateLimiter;
import io.github.resilience4j.retry.Retry;
import io.github.resilience4j.timelimiter.TimeLimiter;

import java.net.http.HttpResponse;
import java.util.concurrent.*;
import java.util.function.Supplier;

public class ExternalService {

    private final Retry retry = ResilienceConfig.retry();
    private final CircuitBreaker circuitBreaker = ResilienceConfig.circuitBreaker();
    private final TimeLimiter timeLimiter = ResilienceConfig.timeLimiter();
    private final RateLimiter rateLimiter = ResilienceConfig.rateLimiter();
    private final ExternalClient externalClient = new ExternalClient();
    private final ScheduledExecutorService executor = new ScheduledThreadPoolExecutor(2);

    public String fetchInfoWithResilience(String uri) {
        Callable<HttpResponse<String>> callable = () -> externalClient.get(uri);

        Callable<HttpResponse<String>> decorated = Decorators.ofCallable(callable)
                .withCircuitBreaker(circuitBreaker)
                .withRetry(retry)
                .decorate();

        try {
            var response = decorated.call();
            return response.body();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public CompletionStage<String> fetchInfoWithResilienceAsync(String uri) {
        Supplier<CompletionStage<String>> supplier = () ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        return fetchInfoWithResilience(uri);
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException(e);
                    }
                }, executor);

        Supplier<CompletionStage<String>> decorated = Decorators
                .ofCompletionStage(supplier)
                .withRateLimiter(rateLimiter)
                .withTimeLimiter(timeLimiter, executor)
                .decorate();

        return decorated.get();
    }

    public CircuitBreaker.State getCircuitBreakerState() {
        return circuitBreaker.getState();
    }
}
