package com.gabrielspassos.poc.service;

import com.gabrielspassos.poc.repository.CalculatorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CalculatorServiceTest {

    @Mock
    private CalculatorRepository calculatorRepository;
    
    @InjectMocks
    private CalculatorService calculatorService;

    @Test
    public void shouldAddTwoNumbers() {
        when(calculatorRepository.save(5)).thenReturn(5);

        int result = calculatorService.add(2, 3);

        assertEquals(5, result);
    }

}