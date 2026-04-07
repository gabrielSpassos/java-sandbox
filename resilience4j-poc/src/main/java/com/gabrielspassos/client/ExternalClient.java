package com.gabrielspassos.client;

import com.gabrielspassos.exception.HttpException;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExternalClient {

    public HttpResponse<String> get(String uri) {
        try (HttpClient client = HttpClient.newHttpClient()) {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(uri))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            IO.println("Response http status: " + response.statusCode());
            IO.println("Response body: " + response.body());

            if (response.statusCode() >= 400) {
                throw new HttpException(response.statusCode(), "Error to get from uri="+uri);
            }

            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
