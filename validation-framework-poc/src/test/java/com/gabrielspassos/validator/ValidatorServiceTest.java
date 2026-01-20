package com.gabrielspassos.validator;

import com.gabrielspassos.dto.BasicDTO1;
import com.gabrielspassos.dto.BasicDTO2;
import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    void shouldValidateNotNullAnnotationAndReturnInvalid() {
        BasicDTO1 dto1 = createBasicDTO1(null, 28);
        ValidatorService<BasicDTO1> validatorService = createValidatorService();

        var result = validatorService.validate(dto1);

        assertNotNull(result);
        assertNotNull(result.left());
        assertFalse(result.left().isEmpty());
        assertEquals(1, result.left().size());
        assertNotNull(result.right());
        assertFalse(result.right());
    }

    @Test
    void shouldValidateAllNotNullAnnotationAndReturnInvalid() {
        BasicDTO1 dto1 = createBasicDTO1(null, null);
        ValidatorService<BasicDTO1> validatorService = createValidatorService();

        var result = validatorService.validate(dto1);

        assertNotNull(result);
        assertNotNull(result.left());
        assertFalse(result.left().isEmpty());
        assertEquals(2, result.left().size());
        assertNotNull(result.right());
        assertFalse(result.right());
    }

    @Test
    void shouldValidateNotEmptyAnnotation() {
        BasicDTO2 dto2 = createBasicDTO2("aaa", List.of(1.0, 2.0), "ccc");
        ValidatorService<BasicDTO2> validatorService = createValidatorService();

        var result = validatorService.validate(dto2);

        assertNotNull(result);
        assertNotNull(result.left());
        assertTrue(result.left().isEmpty());
        assertNotNull(result.right());
        assertTrue(result.right());
    }

    @Test
    void shouldValidateAllNotEmptyAnnotationAndReturnInvalid() {
        BasicDTO2 dto2 = createBasicDTO2("", List.of(), null);
        ValidatorService<BasicDTO2> validatorService = createValidatorService();

        var result = validatorService.validate(dto2);

        assertNotNull(result);
        assertNotNull(result.left());
        assertFalse(result.left().isEmpty());
        assertEquals(3, result.left().size());
        assertNotNull(result.right());
        assertFalse(result.right());
    }

    private <T> ValidatorService<T> createValidatorService() {
        return new ValidatorService<>();
    }

    private BasicDTO1 createBasicDTO1(String name, Integer age) {
        return new BasicDTO1(name, age);
    }

    private BasicDTO2 createBasicDTO2(String a, List<Double> b, String c) {
        return new BasicDTO2(a, b, c);
    }

}