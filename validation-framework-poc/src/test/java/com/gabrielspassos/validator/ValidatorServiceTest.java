package com.gabrielspassos.validator;

import dto.BasicDTO1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorServiceTest {

    @Test
    void shouldValidateNotNullAnnotation() {
        ValidatorService<BasicDTO1> validatorService = createValidatorService();

        assertNotNull(validatorService);
    }

    private <T> ValidatorService<T> createValidatorService() {
        return new ValidatorService<>();
    }

}