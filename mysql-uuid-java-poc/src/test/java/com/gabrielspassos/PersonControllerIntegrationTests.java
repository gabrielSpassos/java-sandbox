package com.gabrielspassos;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.api.request.CreatePersonRequest;
import com.gabrielspassos.api.request.UpdatePersonRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PersonControllerIntegrationTests {

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
    public void shouldCreatePerson() throws IOException, InterruptedException, JSONException {
        var createPersonRequest = new CreatePersonRequest();
        createPersonRequest.setFirstName("Bart");
        createPersonRequest.setLastName("Moe");

        HttpResponse<String> response = createPerson(createPersonRequest);

        var jsonObject = new JSONObject(response.body());

        assertNotNull(jsonObject);
        assertEquals(201, response.statusCode());
        assertFalse(jsonObject.isNull("id"));
        assertFalse(jsonObject.isNull("uuid"));
        assertEquals(createPersonRequest.getFirstName(), jsonObject.getString("firstName"));
        assertEquals(createPersonRequest.getLastName(), jsonObject.getString("lastName"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    public void shouldGetPeople() throws IOException, InterruptedException, JSONException {
        var url = String.format("http://localhost:%s/v1/people", randomServerPort);

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
        assertFalse(jsonArray.getJSONObject(0).isNull("uuid"));
        assertFalse(jsonArray.getJSONObject(0).isNull("firstName"));
        assertFalse(jsonArray.getJSONObject(0).isNull("lastName"));
        assertFalse(jsonArray.getJSONObject(0).isNull("createdAt"));
    }

    @Test
    public void shouldGetPersonById() throws IOException, InterruptedException, JSONException {
        var createPersonRequest = new CreatePersonRequest();
        createPersonRequest.setFirstName("John");
        createPersonRequest.setLastName("Smith");

        HttpResponse<String> createPersonJsonResponse = createPerson(createPersonRequest);
        var createPersonJsonObject = new JSONObject(createPersonJsonResponse.body());
        var personId = createPersonJsonObject.getString("id");

        assertNotNull(createPersonJsonObject);
        assertEquals(201, createPersonJsonResponse.statusCode());
        assertNotNull(personId);

        var url = String.format("http://localhost:%s/v1/people/%s", randomServerPort, personId);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();
        HttpResponse<String> getPersonByIdResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(getPersonByIdResponse.body());

        assertNotNull(jsonObject);
        assertEquals(200, getPersonByIdResponse.statusCode());
        assertEquals(createPersonJsonObject.getString("id"), jsonObject.getString("id"));
        assertEquals(createPersonJsonObject.getString("uuid"), jsonObject.getString("uuid"));
        assertEquals(createPersonJsonObject.getString("firstName"), jsonObject.getString("firstName"));
        assertEquals(createPersonJsonObject.getString("lastName"), jsonObject.getString("lastName"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    public void shouldGetPersonByUUID() throws IOException, InterruptedException, JSONException {
        var createPersonRequest = new CreatePersonRequest();
        createPersonRequest.setFirstName("Mary");
        createPersonRequest.setLastName("Smith");

        HttpResponse<String> createPersonJsonResponse = createPerson(createPersonRequest);
        var createPersonJsonObject = new JSONObject(createPersonJsonResponse.body());
        var personUUID = createPersonJsonObject.getString("uuid");

        assertNotNull(createPersonJsonObject);
        assertEquals(201, createPersonJsonResponse.statusCode());
        assertNotNull(personUUID);

        var url = String.format("http://localhost:%s/v1/people/uuid/%s", randomServerPort, personUUID);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build();
        HttpResponse<String> getPersonByUUIDResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(getPersonByUUIDResponse.body());

        assertNotNull(jsonObject);
        assertEquals(200, getPersonByUUIDResponse.statusCode());
        assertEquals(createPersonJsonObject.getString("id"), jsonObject.getString("id"));
        assertEquals(createPersonJsonObject.getString("uuid"), jsonObject.getString("uuid"));
        assertEquals(createPersonJsonObject.getString("firstName"), jsonObject.getString("firstName"));
        assertEquals(createPersonJsonObject.getString("lastName"), jsonObject.getString("lastName"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    public void shouldUpdatePersonById() throws IOException, InterruptedException, JSONException {
        var createPersonRequest = new CreatePersonRequest();
        createPersonRequest.setFirstName("RoGer");
        createPersonRequest.setLastName("SmITH");

        HttpResponse<String> createPersonJsonResponse = createPerson(createPersonRequest);
        var createPersonJsonObject = new JSONObject(createPersonJsonResponse.body());
        var personId = createPersonJsonObject.getString("id");

        assertNotNull(createPersonJsonObject);
        assertEquals(201, createPersonJsonResponse.statusCode());
        assertNotNull(personId);

        var url = String.format("http://localhost:%s/v1/people/%s", randomServerPort, personId);
        UUID newUUID = UUID.randomUUID();
        var updatePersonRequest = new UpdatePersonRequest();
        updatePersonRequest.setUuid(newUUID);
        updatePersonRequest.setFirstName("Roger");
        updatePersonRequest.setLastName("Smith");

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .PUT(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(updatePersonRequest)))
                .build();
        HttpResponse<String> updatePersonByIdResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(updatePersonByIdResponse.body());

        assertNotNull(jsonObject);
        assertEquals(200, updatePersonByIdResponse.statusCode());
        assertEquals(createPersonJsonObject.getString("id"), jsonObject.getString("id"));
        assertEquals(newUUID.toString(), jsonObject.getString("uuid"));
        assertEquals("Roger", jsonObject.getString("firstName"));
        assertEquals("Smith", jsonObject.getString("lastName"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    @Test
    public void shouldDeletePersonById() throws IOException, InterruptedException, JSONException {
        var createPersonRequest = new CreatePersonRequest();
        createPersonRequest.setFirstName("Mark");
        createPersonRequest.setLastName("Smith");

        HttpResponse<String> createPersonJsonResponse = createPerson(createPersonRequest);
        var createPersonJsonObject = new JSONObject(createPersonJsonResponse.body());
        var personId = createPersonJsonObject.getString("id");

        assertNotNull(createPersonJsonObject);
        assertEquals(201, createPersonJsonResponse.statusCode());
        assertNotNull(personId);

        var url = String.format("http://localhost:%s/v1/people/%s", randomServerPort, personId);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .DELETE()
                .build();
        HttpResponse<String> deletePersonByIdResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        var jsonObject = new JSONObject(deletePersonByIdResponse.body());

        assertNotNull(jsonObject);
        assertEquals(200, deletePersonByIdResponse.statusCode());
        assertEquals(createPersonJsonObject.getString("id"), jsonObject.getString("id"));
        assertEquals(createPersonJsonObject.getString("uuid"), jsonObject.getString("uuid"));
        assertEquals(createPersonJsonObject.getString("firstName"), jsonObject.getString("firstName"));
        assertEquals(createPersonJsonObject.getString("lastName"), jsonObject.getString("lastName"));
        assertFalse(jsonObject.isNull("createdAt"));
    }

    private HttpResponse<String> createPerson(CreatePersonRequest createPersonRequest) throws IOException, InterruptedException {
        var url = String.format("http://localhost:%s/v1/people", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(createPersonRequest)))
                .build();

        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
    }
}
