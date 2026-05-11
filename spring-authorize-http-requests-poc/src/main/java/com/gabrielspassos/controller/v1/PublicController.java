package com.gabrielspassos.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class PublicController {

    @GetMapping("/public/hello")
    public String publicHello() {
        return "Hello Public!";
    }
}
