package com.gabrielspassos;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RoomRentServiceUnitTest {

    private RoomRentService roomRentService = new RoomRentService();

    @Test
    void shouldRentRoom() {
        UUID user = UUID.randomUUID();

        UUID room = roomRentService.rentRoom(user);

        assertNotNull(room);
    }

    @Test
    void shouldFailToRentRoomWithInvalidUser() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> roomRentService.rentRoom(null));

        assertEquals("Invalid user", exception.getMessage());
    }

}