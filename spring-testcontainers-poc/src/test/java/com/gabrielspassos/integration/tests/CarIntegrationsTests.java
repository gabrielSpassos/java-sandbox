package com.gabrielspassos.integration.tests;

import com.gabrielspassos.domain.CarEntity;
import com.gabrielspassos.domain.WarehouseEntity;
import com.gabrielspassos.repository.CarRepository;
import com.gabrielspassos.repository.WarehouseRepository;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.AfterEach;
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
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarIntegrationsTests extends BaseIntegrationTests {

    @LocalServerPort
    private int randomServerPort;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CarRepository carRepository;

    private final List<String> cardIds = new ArrayList<>();
    private final List<String> warehousesIds = new ArrayList<>();

    @AfterEach
    void afterEach() {
        carRepository.deleteAllById(cardIds);
        warehouseRepository.deleteAllById(warehousesIds);
    }

    @Test
    void createCar() throws IOException, InterruptedException, JSONException {
        String warehouseName = "companyTwo";
        String brand = "fiat";
        String name = "argo";
        String url = String.format("http://localhost:%s/v1/warehouses/%s/cars/%s/%s", randomServerPort, warehouseName, brand, name);
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build(), HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        assertFalse(jsonObject.isNull("id"));
        assertFalse(jsonObject.isNull("warehouseId"));
        assertEquals(brand, jsonObject.getString("brand"));
        assertEquals(name, jsonObject.getString("name"));
    }

    @Test
    void findCardById() throws IOException, InterruptedException, JSONException {
        WarehouseEntity warehouseEntity = new WarehouseEntity(UUID.randomUUID().toString(), "companyThree", true);
        WarehouseEntity savedWarehouse = warehouseRepository.save(warehouseEntity);
        warehousesIds.add(savedWarehouse.getId());

        CarEntity carEntity = new CarEntity(UUID.randomUUID().toString(), savedWarehouse.getId(), "GM", "onix", true);
        CarEntity savedCar = carRepository.save(carEntity);
        cardIds.add(savedCar.getId());

        String url = String.format("http://localhost:%s/v1/cars/%s", randomServerPort, savedCar.getId());
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        JSONObject jsonObject = new JSONObject(response.body());

        assertEquals(savedCar.getId(), jsonObject.getString("id"));
        assertEquals(savedWarehouse.getId(), jsonObject.getString("warehouseId"));
        assertEquals("GM", jsonObject.getString("brand"));
        assertEquals("onix", jsonObject.getString("name"));
    }

    @Test
    void findCarsByWarehouseId() throws IOException, InterruptedException, JSONException {
        WarehouseEntity warehouseEntity = new WarehouseEntity(UUID.randomUUID().toString(), "companyFour", true);
        WarehouseEntity savedWarehouse = warehouseRepository.save(warehouseEntity);
        warehousesIds.add(savedWarehouse.getId());

        CarEntity car1 = new CarEntity("6a288cd8-a070-45c0-a7a0-7e477bb897d1", savedWarehouse.getId(), "Volks", "Fox", true);
        CarEntity car2 = new CarEntity("7a288cd8-a070-45c0-a7a0-7e477bb897d1", savedWarehouse.getId(), "Pegout", "208", true);
        carRepository.saveAll(Arrays.asList(car1, car2)).forEach(carEntity -> cardIds.add(carEntity.getId()));

        String url = String.format("http://localhost:%s/v1/warehouses/%s/cars", randomServerPort, savedWarehouse.getId());
        HttpClient httpClient = HttpClient.newBuilder().build();

        HttpResponse<String> response = httpClient.send(HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header(CONTENT_TYPE, APPLICATION_JSON_VALUE)
                .header(ACCEPT, APPLICATION_JSON_VALUE)
                .GET()
                .build(), HttpResponse.BodyHandlers.ofString());

        JSONArray jsonArray = new JSONArray(response.body());

        assertEquals(2, jsonArray.length());
        assertEquals(car1.getId(), jsonArray.getJSONObject(0).getString("id"));
        assertEquals(savedWarehouse.getId(), jsonArray.getJSONObject(0).getString("warehouseId"));
        assertEquals("Volks", jsonArray.getJSONObject(0).getString("brand"));
        assertEquals("Fox", jsonArray.getJSONObject(0).getString("name"));
        assertEquals(car2.getId(), jsonArray.getJSONObject(1).getString("id"));
        assertEquals(savedWarehouse.getId(), jsonArray.getJSONObject(1).getString("warehouseId"));
        assertEquals("Pegout", jsonArray.getJSONObject(1).getString("brand"));
        assertEquals("208", jsonArray.getJSONObject(1).getString("name"));
    }

}
