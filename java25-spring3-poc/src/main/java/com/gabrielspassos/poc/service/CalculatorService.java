package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.repository.CalculatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {

    @Autowired
    private CalculatorRepository calculatorRepository;

    public int add(int a, int b) {
        var result = a + b;
        calculatorRepository.save(result);
        return result;
    }

}
