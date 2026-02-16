package com.gabrielspassos.poc.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gabrielspassos.poc.controller.request.CalculatorRequest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculatorControllerTest {

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
    public void shouldAddTwoNumbers() throws IOException, InterruptedException, JSONException {
        var a = 5;
        var b = 3;
        var request = new CalculatorRequest(a, b);
        var url = String.format("http://localhost:%s/v1/add", randomServerPort);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.ofString(mapper.writeValueAsString(request)))
                .build();

        HttpResponse<String> response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

        assertEquals(200, response.statusCode());
        assertNotNull(response.body());
        var responseBody = new JSONObject(response.body());
        assertEquals(8, responseBody.getInt("result"));
    }

}