package org.gabrielspassos.service;

import org.gabrielspassos.dto.UserDTO;
import org.gabrielspassos.fakes.repository.FakeUserRepository;
import org.gabrielspassos.repository.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserServiceTest {

    @Test
    void shouldReturnActiveUser() {
        UserRepository userRepository = new UserRepository();
        UserService userService = new UserService();

        // using setter injection
        userService.setUserRepository(userRepository);
        List<UserDTO> activeUsers = userService.findActiveUsers();

        assertNotNull(activeUsers);
        assertFalse(activeUsers.isEmpty());
        assertEquals(1, activeUsers.size());
    }

    @Test
    void shouldReturnMultipleActiveUser() {
        UserRepository userRepository = new FakeUserRepository();
        UserService userService = new UserService();

        userService.setUserRepository(userRepository);
        List<UserDTO> activeUsers = userService.findActiveUsers();

        assertNotNull(activeUsers);
        assertFalse(activeUsers.isEmpty());
        assertEquals(2, activeUsers.size());
    }

}