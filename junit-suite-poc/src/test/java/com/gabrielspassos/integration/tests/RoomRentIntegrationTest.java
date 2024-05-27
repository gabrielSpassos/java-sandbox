package com.gabrielspassos.integration.tests;

import com.gabrielspassos.RoomRentService;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RoomRentIntegrationTest extends BaseIntegrationTest {

    @Test
    void shouldRentRoomWithValidUser() {
        RoomRentService roomRentService = new RoomRentService();

        UUID room = roomRentService.rentRoom(user);

        assertNotNull(room);
    }
}
