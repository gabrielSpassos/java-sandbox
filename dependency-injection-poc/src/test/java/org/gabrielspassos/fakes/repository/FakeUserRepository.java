package org.gabrielspassos.fakes.repository;

import org.gabrielspassos.dto.UserDTO;
import org.gabrielspassos.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class FakeUserRepository extends UserRepository {

    @Override
    public List<UserDTO> findAll() {
        UserDTO userDTO1 = new UserDTO(UUID.randomUUID().toString(), "user@email.com", true);
        UserDTO userDTO2 = new UserDTO(UUID.randomUUID().toString(), "user2@email.com", true);
        return Arrays.asList(userDTO1, userDTO2);
    }
}
