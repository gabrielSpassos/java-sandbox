package com.gabrielspassos.poc.services;

import com.gabrielspassos.poc.dto.EmployeeDTO;
import com.gabrielspassos.poc.dto.PersonDTO;
import com.gabrielspassos.poc.dtos.PairDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AbstractClassServiceTest {

    private static IClassService classService;

    @BeforeAll
    static void setup() {
        classService = CacheableClassService.getCacheableClassService();
    }

    @Test
    void shouldReturnMapOfMatchingFields() {
        List<PairDTO<Field, Field>> matchingFields = classService.getMatchingFields(PersonDTO.class, EmployeeDTO.class);

        assertNotNull(matchingFields);
        assertEquals(3, matchingFields.size());
        assertEquals("name", matchingFields.get(0).getLeft().getName());
        assertEquals("name", matchingFields.get(0).getRight().getName());

        assertEquals("age", matchingFields.get(1).getLeft().getName());
        assertEquals("age", matchingFields.get(1).getRight().getName());

        assertEquals("name", matchingFields.get(2).getLeft().getName());
        assertEquals("firstName", matchingFields.get(2).getRight().getName());
    }

}