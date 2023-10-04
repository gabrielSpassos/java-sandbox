package org.gabrielspassos.service;

import org.gabrielspassos.dto.UserDTO;
import org.gabrielspassos.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDTO> findActiveUsers() {
        return userRepository.findAll().stream()
                .filter(UserDTO::getIsActive)
                .collect(Collectors.toList());
    }

}
