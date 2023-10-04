package org.gabrielspassos.repository;

import org.gabrielspassos.dto.UserDTO;

import java.util.List;

public interface UserRepository {

    List<UserDTO> findAll();

}
