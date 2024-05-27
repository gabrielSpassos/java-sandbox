package com.gabrielspassos.integration.tests;

import com.gabrielspassos.CarRentService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CarRentIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldRentCarWithValidUser() {
        CarRentService carRentService = new CarRentService();

        UUID car = carRentService.rentCar(user);

        assertNotNull(car);
    }
}
