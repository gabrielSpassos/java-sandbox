package com.gabrielspassos.service;

import com.gabrielspassos.client.ExternalClient;
import com.gabrielspassos.config.ResilienceConfig;
import io.github.resilience4j.decorators.Decorators;

import java.net.http.HttpResponse;
import java.util.concurrent.Callable;

public class ExternalService {

    private final ExternalClient externalClient = new ExternalClient();

    public String fetchInfoWithResilience(String uri) {
        var circuitBreaker = ResilienceConfig.circuitBreaker();
        var retry = ResilienceConfig.retry();

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
}
