package org.gabrielspassos.repository;

import org.gabrielspassos.dto.UserDTO;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public interface LocalUserRepositoryComponent extends UserRepositoryComponent {

    default UserRepository getUserRepository() {
        return new UserRepository() {
            @Override
            public List<UserDTO> findAll() {
                UserDTO userDTO = new UserDTO(UUID.randomUUID().toString(), "user@email.com", true);
                return Collections.singletonList(userDTO);
            }
        };
    }
}
