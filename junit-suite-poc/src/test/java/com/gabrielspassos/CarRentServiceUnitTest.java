package com.gabrielspassos;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class CarRentServiceUnitTest {

    CarRentService carRentService = new CarRentService();

    @Test
    void shouldRentCar() {
        UUID user = UUID.randomUUID();

        UUID carRent = carRentService.rentCar(user);

        assertNotNull(carRent);
    }

    @Test
    void shouldFailToRentCarWithInvalidUser() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> carRentService.rentCar(null));

        assertEquals("Invalid user", exception.getMessage());
    }

}