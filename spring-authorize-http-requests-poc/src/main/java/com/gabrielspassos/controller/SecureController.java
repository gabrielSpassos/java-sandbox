package com.gabrielspassos.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/user/hello")
    public String userHello() {
        return "Hello User!";
    }

}