package com.gabrielspassos.service;

import com.gabrielspassos.TestcontainersConfiguration;
import com.gabrielspassos.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @BeforeEach
    void setup() {
        userService.deleteAll();
    }

    @Test
    void shouldCreateUserSuccessfully() {
        var user = new User(UUID.randomUUID().toString(), "Gabriel", LocalDate.parse("1999-01-01"));

        var saved = userService.save(user);

        assertNotNull(saved);
        assertEquals(user.id(), saved.id());
        assertEquals("Gabriel", saved.name());
        assertEquals(user.birthDate(), saved.birthDate());
    }

    @Test
    void shouldFindById() {
        var user = createUser();

        Optional<User> byId = userService.findById(user.id());

        assertTrue(byId.isPresent());
        assertEquals("Gabriel", byId.get().name());
    }

    @Test
    void shouldNotFindById() {
        Optional<User> byId = userService.findById(UUID.randomUUID().toString());

        assertTrue(byId.isEmpty());
    }

    @Test
    void shouldFindByName() {
        var user = createUser();

        Optional<User> byName = userService.findByName("Gabriel");

        assertTrue(byName.isPresent());
        assertEquals("Gabriel", byName.get().name());
    }

    @Test
    void shouldDelete() {
        var user = createUser();

        boolean delete = userService.delete(user.id());

        assertTrue(delete);
    }

    @Test
    void shouldFindAll() {
        var user1 = createUser();
        var user2 = createUser();

        List<User> all = userService.findAll();

        assertNotNull(all);
        assertFalse(all.isEmpty());
        assertEquals(2, all.size());
    }

    private User createUser() {
        var user = new User(UUID.randomUUID().toString(), "Gabriel", LocalDate.parse("1999-01-01"));

        return userService.save(user);
    }
}