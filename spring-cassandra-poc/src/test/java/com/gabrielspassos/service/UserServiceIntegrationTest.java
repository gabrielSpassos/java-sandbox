package com.gabrielspassos.service;

import com.gabrielspassos.TestcontainersConfiguration;
import com.gabrielspassos.domain.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    void shouldCreateUserSuccessfully() {
        var user = new User(UUID.randomUUID().toString(), "Gabriel", LocalDate.parse("1999-01-01"));

        var saved = userService.save(user);

        assertNotNull(saved);
        assertEquals(user.id(), saved.id());
        assertEquals("Gabriel", saved.name());
        assertEquals(user.birthDate(), saved.birthDate());
    }
}