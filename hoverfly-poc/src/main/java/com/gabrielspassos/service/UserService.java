package com.gabrielspassos.service;

import com.gabrielspassos.entity.UserEntity;
import com.gabrielspassos.exception.NotFoundException;
import com.gabrielspassos.mapper.UUIDMapper;
import com.gabrielspassos.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserEntity create(String name) {
        UserEntity userEntity = new UserEntity();
        userEntity.setName(name);

        return userRepository.save(userEntity);
    }

    public UserEntity findByName(String name) {
        return userRepository.findByName(name)
                .orElseThrow(() -> new NotFoundException("User not found", "USER_NOT_FOUND"));
    }

    public UserEntity findById(String id) {
        UUID userId = UUIDMapper.toUUID(id);

        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found", "USER_NOT_FOUND"));
    }
}
