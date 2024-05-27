package com.gabrielspassos;

import java.util.UUID;

public class RoomRentService {

    public UUID rentRoom(UUID user) {
        if (null == user) {
            throw new IllegalArgumentException("Invalid user");
        }

        return UUID.randomUUID();
    }

}
