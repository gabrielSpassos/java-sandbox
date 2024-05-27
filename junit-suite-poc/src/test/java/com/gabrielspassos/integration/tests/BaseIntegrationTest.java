package com.gabrielspassos.integration.tests;

import com.gabrielspassos.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class BaseIntegrationTest {

    static UserService userService = new UserService();
    static UUID user;

    @BeforeAll
    public static void setUp() {
        System.out.println("Before all integration tests");
        user = userService.createUser();
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("After all integration tests");
        assertNotNull(user);
    }
}
