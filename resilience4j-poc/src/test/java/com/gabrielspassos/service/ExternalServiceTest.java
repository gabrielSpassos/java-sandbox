package com.gabrielspassos.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.Scenario;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.*;

class ExternalServiceTest {

    private static WireMockServer wireMock;

    @BeforeAll
    static void setup() {
        wireMock = new WireMockServer(8089);
        wireMock.start();
        configureFor("localhost", 8089);
    }

    @AfterAll
    static void teardown() {
        wireMock.stop();
    }

    @BeforeEach
    void reset() {
        wireMock.resetAll();
    }

    @Test
    void shouldRetryAndEventuallySucceed() {
        stubFor(get("/external")
                .inScenario("retry")
                .whenScenarioStateIs(Scenario.STARTED)
                .willReturn(serverError().withBody("server error"))
                .willSetStateTo("second"));

        stubFor(get("/external")
                .inScenario("retry")
                .whenScenarioStateIs("second")
                .willReturn(serverError().withBody("server error"))
                .willSetStateTo("third"));

        stubFor(get("/external")
                .inScenario("retry")
                .whenScenarioStateIs("third")
                .willReturn(ok("success")));

        var service = new ExternalService();

        String result = service.fetchInfoWithResilience("http://localhost:8089/external");

        assertEquals("success", result);

        verify(3, getRequestedFor(urlEqualTo("/external")));
    }

    @Test
    void shouldOpenCircuitBreakerAndReturnFallback() {
        String uri = "http://localhost:8089/external";
        stubFor(get("/external").willReturn(serverError().withBody("server error")));

        var service = new ExternalService();

        // Force failures
        for (int i = 0; i < 1; i++) {
            service.fetchInfoWithResilience(uri);
        }

        String result = service.fetchInfoWithResilience(uri);

        assertNotNull(result);
    }
}