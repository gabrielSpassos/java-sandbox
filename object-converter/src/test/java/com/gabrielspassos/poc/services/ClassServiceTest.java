package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.dto.AccountDTO;
import com.gabrielspassos.poc.reflections.services.ClassService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ClassServiceTest {

    private ClassService classService;

    @BeforeEach
    void initService() {
        classService = ClassService.getClassService();
    }

    @Test
    void shouldReturnAttributeNamesAndValues() throws IllegalAccessException {
        AccountDTO account = new AccountDTO("0001", "12345", 6L);

        Map<Field, Object> attributesAndValuesFromObject
                = classService.getAttributesAndValuesFromObject(account);

        assertFalse(attributesAndValuesFromObject.isEmpty());
        Field agency = getFieldByName(attributesAndValuesFromObject, "agency");
        assertNotNull(agency);
        assertEquals("0001", agency.get(account));

        Field number = getFieldByName(attributesAndValuesFromObject, "number");
        assertNotNull(number);
        assertEquals("12345", number.get(account));

        Field digit = getFieldByName(attributesAndValuesFromObject, "digit");
        assertNotNull(digit);
        assertEquals(6L, digit.get(account));
    }

    private Field getFieldByName(Map<Field, Object> attributesAndValuesFromObject, String attributeName) {
        return attributesAndValuesFromObject.keySet()
                .stream()
                .filter(field -> field.getName().equals(attributeName))
                .findAny()
                .get();
    }

}