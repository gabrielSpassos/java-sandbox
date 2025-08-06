package com.gabrielspassos.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DungeonControllerIntegrationTest {

    private static HttpClient httpClient;
    private static ObjectMapper mapper;

    @LocalServerPort
    private int randomServerPort;

    @BeforeAll
    static void beforeAll() {
        httpClient = HttpClient.newBuilder().build();
        mapper = new ObjectMapper();
    }

    @Test
    public void shouldCalculateMinimumDungeonHealth() throws IOException, InterruptedException {
        // Given
        var id = UUID.randomUUID().toString();
        CalculateDungeonHealthRequest request = new CalculateDungeonHealthRequest(id, List.of(
            List.of(0, -2, 0),
            List.of(-3, -5, 0),
            List.of(0, -10, 0)
        ));

        // When
        var url = String.format("http://localhost:%s/v1/dungeons", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(request)))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(200, response.statusCode());
        assertEquals("1", response.body());
    }

}