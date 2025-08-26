package com.gabrielspassos.stress;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import io.gatling.javaapi.core.ChainBuilder;
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

    private final ScenarioBuilder loadScenario = scenario("Calculate Minimum Health")
            .exec(http("calculate-minimum-health")
                    .post("/v1/dungeons")
                    .body(StringBody(session -> buildRequestBody(UUID.randomUUID(), dungeon)))
                    .check(status().is(200))
                    .check(jsonPath("$.id").saveAs("executionId"))
                    .check(jsonPath("$.dungeon").exists())
                    .check(jsonPath("$.minimalHealth").exists())
                    .check(responseTimeInMillis().saveAs("calculateResponseTime"))
            ).exec(session -> {
                String calculateResponseTime = session.getString("calculateResponseTime");
                return session.set("calculateResponseTimeMetric", calculateResponseTime);
            })
            .exec(sendMetricToPrometheus("dungeon_calculate_response_time_ms", "#{calculateResponseTimeMetric}"))
            .pause(1, 5) // Pause between 1 and 5 seconds
            .exec(http("get-dungeon-by-id")
                    .get(session -> "/v1/dungeons/" + session.getString("executionId")) // use saved ID
                    .check(status().is(200))
                    .check(jsonPath("$.id").exists())
                    .check(jsonPath("$.dungeon").exists())
                    .check(jsonPath("$.minimalHealth").exists())
                    .check(responseTimeInMillis().saveAs("getResultResponseTime"))
            ).exec(session -> {
                String getResultResponseTime = session.getString("getResultResponseTime");
                return session.set("getResponseTimeMetric", getResultResponseTime);
            })
            .exec(sendMetricToPrometheus("dungeon_get_response_time_ms", "#{getResponseTimeMetric}"))
            .pause(1, 5) // Pause between 1 and 5 seconds
            .exec(http("not-found-dungeon-by-id")
                    .get(session -> "/v1/dungeons/" + UUID.randomUUID()) // use random ID
                    .check(status().is(404))
                    .check(jsonPath("$.message").exists())
                    .check(responseTimeInMillis().saveAs("getResultResponseTime"))
            ).exec(session -> {
                String getResultResponseTime = session.getString("getResultResponseTime");
                return session.set("getResponseTimeMetric", getResultResponseTime);
            })
            .exec(sendMetricToPrometheus("dungeon_get_response_time_ms", "#{getResponseTimeMetric}"));

    private ChainBuilder sendMetricToPrometheus(String metricName, String value) {
        String metricData = String.format("%s %s\n", metricName, value);

        return exec(http("Push Metric")
                .put("http://localhost:9091/metrics/job/dungeon_stress_test")
                .body(StringBody(metricData))
                .header("Content-Type", "text/plain")
                .check(status().is(200))
        );
    }

    {
        setUp(
                loadScenario.injectOpen(
                        rampUsersPerSec(1).to(10).during(30),
                        constantUsersPerSec(10).during(60)
                )
        )
                .protocols(httpProtocol)
                .assertions(
                        global().responseTime().max().lt(5000),
                        global().successfulRequests().percent().gt(99.0)
                );
    }

}
