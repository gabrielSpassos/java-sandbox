package com.gabrielspassos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.controller.request.CalculateDungeonHealthRequest;
import com.gabrielspassos.repository.GameRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DungeonControllerIntegrationTest {

    private static HttpClient httpClient;
    private static ObjectMapper mapper;

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private GameRepository gameRepository;

    private static PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withUsername("sa")
            .withPassword("test")
            .withExposedPorts(5432)
            .withInitScript("schema.sql");

    @BeforeAll
    static void beforeAll() {
        httpClient = HttpClient.newBuilder().build();
        mapper = new ObjectMapper();
        postgresContainer.start();
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }

    @AfterEach
    void afterEach() {
        gameRepository.deleteAll();
    }

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);
    }

    @Test
    public void shouldCalculateMinimumDungeonHealth() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();
        var dungeon = List.of(
                List.of(-2, -3, 3),
                List.of(-5, -10, 1),
                List.of(10, 30, -5)
        );

        // When
        HttpResponse<String> response = sendCalculateDungeonHealthRequest(id, dungeon);

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(id.toString(), responseBody.getString("id"));
        assertEquals(dungeon.toString().replace(" ", ""), responseBody.getString("dungeon"));
        assertEquals(7, responseBody.getInt("minimalHealth"));
    }

    @Test
    public void shouldCalculateMinimumDungeonHealthWithoutDungeon() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();

        // When
        HttpResponse<String> response = sendCalculateDungeonHealthRequest(id, null);

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(id.toString(), responseBody.getString("id"));
        assertTrue(responseBody.isNull("dungeon"));
        assertEquals(1, responseBody.getInt("minimalHealth"));
    }

    @Test
    public void shouldCalculateMinimumDungeonHealthWithEmptyDungeon() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();

        // When
        HttpResponse<String> response = sendCalculateDungeonHealthRequest(id, new ArrayList<>());

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(id.toString(), responseBody.getString("id"));
        assertEquals("[]", responseBody.getString("dungeon"));
        assertEquals(1, responseBody.getInt("minimalHealth"));
    }

    @Test
    public void shouldCalculateMinimumDungeonHealthWithEmptyInternalDungeon() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();

        // When
        HttpResponse<String> response = sendCalculateDungeonHealthRequest(id, new ArrayList<>(new ArrayList<>()));

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(id.toString(), responseBody.getString("id"));
        assertEquals("[]", responseBody.getString("dungeon"));
        assertEquals(1, responseBody.getInt("minimalHealth"));
    }

    @Test
    public void shouldFindAllResponses() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();
        var dungeon = List.of(List.of(1));

        HttpResponse<String> createResponse = sendCalculateDungeonHealthRequest(id, dungeon);
        assertEquals(200, createResponse.statusCode());

        // When
        var url = String.format("http://localhost:%s/v1/dungeons", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONArray(response.body());
        assertEquals(1, responseBody.length());
    }

    @Test
    public void shouldFindEmptyResponses() throws IOException, InterruptedException, JSONException {
        // When
        var url = String.format("http://localhost:%s/v1/dungeons", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONArray(response.body());
        assertEquals(0, responseBody.length());
    }

    @Test
    public void shouldFindById() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();
        var dungeon = List.of(List.of(1));

        HttpResponse<String> createResponse = sendCalculateDungeonHealthRequest(id, dungeon);
        assertEquals(200, createResponse.statusCode());

        // When
        var url = String.format("http://localhost:%s/v1/dungeons/%s", randomServerPort, id);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(id.toString(), responseBody.getString("id"));
        assertEquals(dungeon.toString(), responseBody.getString("dungeon"));
        assertEquals(1, responseBody.getInt("minimalHealth"));
    }

    @Test
    public void shouldNotFindById() throws IOException, InterruptedException, JSONException {
        // Given
        var id = UUID.randomUUID();

        // When
        var url = String.format("http://localhost:%s/v1/dungeons/%s", randomServerPort, id);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        // Then
        assertEquals(404, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertFalse(responseBody.isNull("message"));
    }

    private HttpResponse<String> sendCalculateDungeonHealthRequest(UUID id, List<List<Integer>> dungeon) throws IOException, InterruptedException {
        var request = new CalculateDungeonHealthRequest(id.toString(), dungeon);
        var url = String.format("http://localhost:%s/v1/dungeons", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(request)))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }

}