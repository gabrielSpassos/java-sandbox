package com.gabrielspassos.integration.tests;

import com.gabrielspassos.RoomRentService;
import com.gabrielspassos.UserService;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class RoomRentIntegrationTest {

    @Test
    public void shouldRentRoomWithValidUser() {
        UserService userService = new UserService();
        RoomRentService roomRentService = new RoomRentService();

        UUID user = userService.createUser();

        UUID room = roomRentService.rentRoom(user);

        assertNotNull(room);
    }
}
