package com.gabrielspassos.integration.tests;

import com.gabrielspassos.CarRentService;
import com.gabrielspassos.UserService;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class CarRentIntegrationTest {

    @Test
    public void shouldRentCarWithValidUser() {
        UserService userService = new UserService();
        CarRentService carRentService = new CarRentService();

        UUID user = userService.createUser();

        UUID car = carRentService.rentCar(user);

        assertNotNull(car);
    }
}
