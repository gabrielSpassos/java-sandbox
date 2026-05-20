package com.gabrielspassos.controller.v1;

import com.gabrielspassos.controller.v1.request.UserRequest;
import com.gabrielspassos.controller.v1.response.UserResponse;
import com.gabrielspassos.mapper.UserMapper;
import com.gabrielspassos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        var user = userService.create(request.name());
        var userResponse = UserMapper.toResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

}
