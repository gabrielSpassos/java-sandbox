package com.gabrielspassos;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;

public class RoomRentServiceUnitTest {

    private RoomRentService roomRentService = new RoomRentService();

    @Test
    public void shouldRentRoom() {
        UUID user = UUID.randomUUID();

        UUID room = roomRentService.rentRoom(user);

        assertNotNull(room);
    }

    @Test
    public void shouldFailToRentRoomWithInvalidUser() {
        IllegalArgumentException exception
                = assertThrows(IllegalArgumentException.class, () -> roomRentService.rentRoom(null));

        assertEquals("Invalid user", exception.getMessage());
    }

}