package com.gabrielspassos;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;


public class CarRentServiceUnitTest {

    CarRentService carRentService = new CarRentService();

    @Test
    public void shouldRentCar() {
        UUID user = UUID.randomUUID();

        UUID carRent = carRentService.rentCar(user);

        assertNotNull(carRent);
    }

    @Test
    public void shouldFailToRentCarWithInvalidUser() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> carRentService.rentCar(null));

        assertEquals("Invalid user", exception.getMessage());
    }

}