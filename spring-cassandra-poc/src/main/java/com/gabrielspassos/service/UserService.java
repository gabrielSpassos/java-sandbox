package com.gabrielspassos.service;

import com.gabrielspassos.domain.User;
import com.gabrielspassos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByName(String name) {
        return userRepository.findByName(name);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public boolean delete(String id) {
        userRepository.deleteById(id);
        return true;
    }

    boolean deleteAll() {
        userRepository.deleteAll();
        return true;
    }
}
