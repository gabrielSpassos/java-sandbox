package com.gabrielspassos.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.api.request.PetRequest;
import com.gabrielspassos.repository.PetRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PetControllerIntegrationTest {

    @Autowired
    private PetRepository petRepository;

    private static HttpClient httpClient;
    private static ObjectMapper mapper;

    @LocalServerPort
    private int randomServerPort;

    private List<String> petIds = new ArrayList<>();

    @BeforeAll
    static void beforeAll() {
        httpClient = HttpClient.newBuilder().build();
        mapper = new ObjectMapper();
    }

    @AfterEach
    void cleanUp() {
        petIds.forEach(id -> petRepository.deleteById(id));
    }

    @Test
    void shouldCreatePet() throws JSONException, IOException, InterruptedException {
        var petRequest = new PetRequest();
        petRequest.setName("Bobby");

        HttpResponse<String> createPetResponse = createPet(petRequest);

        var jsonObject = new JSONObject(createPetResponse.body());

        assertNotNull(jsonObject);
        assertEquals(201, createPetResponse.statusCode());
        assertFalse(jsonObject.isNull("id"));
        assertEquals(petRequest.getName(), jsonObject.getString("name"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    void shouldGetPets() throws JSONException, IOException, InterruptedException {
        var url = String.format("http://localhost:%s/v1/pets", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonArray = new JSONArray(response.body());

        assertNotNull(jsonArray);
        assertEquals(200, response.statusCode());
        assertTrue(jsonArray.length() > 0);
        assertFalse(jsonArray.getJSONObject(0).isNull("id"));
        assertFalse(jsonArray.getJSONObject(0).isNull("name"));
        assertFalse(jsonArray.getJSONObject(0).isNull("createdAt"));
    }

    @Test
    void shouldGetPetById() throws JSONException, IOException, InterruptedException {
        var petRequest = new PetRequest();
        petRequest.setName("Buddy");

        HttpResponse<String> createPetResponse = createPet(petRequest);

        var createPetJsonObject = new JSONObject(createPetResponse.body());
        var petId = createPetJsonObject.getString("id");

        assertNotNull(createPetJsonObject);
        assertEquals(201, createPetResponse.statusCode());
        assertNotNull(petId);

        var url = String.format("http://localhost:%s/v1/pets/%s", randomServerPort, petId);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(response.body());

        assertNotNull(jsonObject);
        assertEquals(200, response.statusCode());
        assertEquals(petId, jsonObject.getString("id"));
        assertEquals(petRequest.getName(), jsonObject.getString("name"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    void shouldUpdatePetById() throws JSONException, IOException, InterruptedException {
        var petRequest = new PetRequest();
        petRequest.setName("MaRLey");

        HttpResponse<String> createPetResponse = createPet(petRequest);

        var createPetJsonObject = new JSONObject(createPetResponse.body());
        var petId = createPetJsonObject.getString("id");

        assertNotNull(createPetJsonObject);
        assertEquals(201, createPetResponse.statusCode());
        assertNotNull(petId);

        var url = String.format("http://localhost:%s/v1/pets/%s", randomServerPort, petId);
        var updateRequest = new PetRequest();
        updateRequest.setName("Marley");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(updateRequest)))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(response.body());

        assertNotNull(jsonObject);
        assertEquals(200, response.statusCode());
        assertEquals(petId, jsonObject.getString("id"));
        assertEquals(updateRequest.getName(), jsonObject.getString("name"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    void shouldDeletePetById() throws JSONException, IOException, InterruptedException {
        var petRequest = new PetRequest();
        petRequest.setName("Max");

        HttpResponse<String> createPetResponse = createPet(petRequest);

        var createPetJsonObject = new JSONObject(createPetResponse.body());
        var petId = createPetJsonObject.getString("id");

        assertNotNull(createPetJsonObject);
        assertEquals(201, createPetResponse.statusCode());
        assertNotNull(petId);

        var url = String.format("http://localhost:%s/v1/pets/%s", randomServerPort, petId);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .DELETE()
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(response.body());

        assertNotNull(jsonObject);
        assertEquals(200, response.statusCode());
        assertEquals(petId, jsonObject.getString("id"));
        assertEquals(petRequest.getName(), jsonObject.getString("name"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    private HttpResponse<String> createPet(PetRequest petRequest) throws IOException, InterruptedException, JSONException {
        var url = String.format("http://localhost:%s/v1/pets", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(petRequest)))
                .build();

        HttpResponse<String> createPetJsonResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var createPersonJsonObject = new JSONObject(createPetJsonResponse.body());
        var petId = createPersonJsonObject.getString("id");
        petIds.add(petId);

        return createPetJsonResponse;
    }

}