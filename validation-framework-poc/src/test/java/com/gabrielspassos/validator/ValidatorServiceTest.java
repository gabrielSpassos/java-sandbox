package com.gabrielspassos.validator;

import com.gabrielspassos.dto.BasicDTO1;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidatorServiceTest {

    @Test
    void shouldThrowErrorToValidateNullObject() {
        ValidatorService<BasicDTO1> validatorService = createValidatorService();

        var result = validatorService.validate(null);

        assertNotNull(result);
        assertNotNull(result.left());
        assertFalse(result.left().isEmpty());
        assertNotNull(result.right());
        assertFalse(result.right());
    }

    @Test
    void shouldValidateNotNullAnnotation() {
        BasicDTO1 dto1 = createBasicDTO1("Gabriel", 28);
        ValidatorService<BasicDTO1> validatorService = createValidatorService();

        var result = validatorService.validate(dto1);

        assertNotNull(result);
        assertNotNull(result.left());
        assertTrue(result.left().isEmpty());
        assertNotNull(result.right());
        assertTrue(result.right());
    }

    private <T> ValidatorService<T> createValidatorService() {
        return new ValidatorService<>();
    }

    private BasicDTO1 createBasicDTO1(String name, int age) {
        return new BasicDTO1(name, age);
    }

}