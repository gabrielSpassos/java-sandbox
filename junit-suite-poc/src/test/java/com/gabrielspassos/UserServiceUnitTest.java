package com.gabrielspassos;

import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class UserServiceUnitTest {

    UserService userService = new UserService();

    @Test
    public void shouldCreateUser() {
        UUID user = userService.createUser();

        assertNotNull(user);
    }

}