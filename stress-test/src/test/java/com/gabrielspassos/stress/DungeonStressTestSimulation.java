package com.gabrielspassos.stress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;

import java.util.List;
import java.util.UUID;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;


public class DungeonStressTestSimulation extends Simulation {

    private static final String BASE_URL = "http://localhost:8080";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // HTTP protocol configuration
    private final HttpProtocolBuilder httpProtocol = http
            .baseUrl(BASE_URL)
            .acceptHeader("application/json")
            .contentTypeHeader("application/json")
            .userAgentHeader("Gatling/DungeonGame");

    // Test data
    private final List<List<Integer>> dungeon = List.of(
            List.of(-2, -3, 3),
            List.of(-5, -10, 1),
            List.of(10, 30, -5)
    );

    private String buildRequestBody(UUID id, List<List<Integer>> dungeon) {
        try {
            var request = new CalculateDungeonHealthRequest(id.toString(), dungeon);
            return objectMapper.writeValueAsString(request);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create request body", e);
        }
    }

    private final ScenarioBuilder basicLoad = scenario("Calculate Minimum Health")
            .exec(http("calculate-minimum-health")
                    .post("/v1/dungeons")
                    .body(StringBody(buildRequestBody(UUID.randomUUID(), dungeon)))
                    .check(status().is(200))
                    .check(jsonPath("$.id").exists())
                    //.check(jsonPath("$.dungeon").exists())
                    .check(jsonPath("$.minimalHealth").exists())
            );

    {
        setUp(
                basicLoad.injectOpen(
                        rampUsersPerSec(1).to(10).during(30),
                        constantUsersPerSec(10).during(60)
                )
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(3000),
                        global().successfulRequests().percent().gt(99.0)
                );
    }

}
