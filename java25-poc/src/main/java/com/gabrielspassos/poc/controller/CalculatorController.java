package com.gabrielspassos.poc.controller;

import com.gabrielspassos.poc.controller.request.CalculatorRequest;
import com.gabrielspassos.poc.controller.response.CalculatorResponse;
import com.gabrielspassos.poc.service.CalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping("/v1/add")
    public ResponseEntity<CalculatorResponse> add(@RequestBody CalculatorRequest request) {
        var result = calculatorService.add(request.getA(), request.getB());
        return ResponseEntity.ok(new CalculatorResponse(result));
    }

}
