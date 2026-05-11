package com.gabrielspassos.controller.v1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1")
public class AdminController {

    @GetMapping("/admin/hello")
    public String adminHello() {
        return "Hello Admin!";
    }
}