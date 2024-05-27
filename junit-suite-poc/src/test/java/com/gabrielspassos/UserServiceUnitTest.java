package com.gabrielspassos;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;


class UserServiceUnitTest {

    UserService userService = new UserService();

    @Test
    void shouldCreateUser() {
        UUID user = userService.createUser();

        assertNotNull(user);
    }

}