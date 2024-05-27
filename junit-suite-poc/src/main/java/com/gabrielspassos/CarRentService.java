package com.gabrielspassos;

import java.util.UUID;

public class CarRentService {

    public UUID rentCar(UUID user) {
        if (null == user) {
            throw new IllegalArgumentException("Invalid user");
        }

        return UUID.randomUUID();
    }
}
