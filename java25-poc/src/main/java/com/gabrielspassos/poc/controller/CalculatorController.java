package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping
    public ResponseEntity<Integer> add(int a, int b) {
        var result = calculatorService.add(a, b);
        return ResponseEntity.ok(result);
    }
    
}
