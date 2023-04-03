package com.gabrielspassos.poc.utils;

import com.gabrielspassos.poc.utils.dto.PersonDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderingUtilsTest {

    private static OrderingUtils orderingUtils;

    @BeforeAll
    static void setup() {
        orderingUtils = new OrderingUtils();
    }

    @Test
    void shouldOrderPeopleByName() {
        List<PersonDTO> people = Arrays.asList(
                new PersonDTO("Joao", 12),
                new PersonDTO("Ana", 30),
                new PersonDTO("Felipe", 23));

        List<PersonDTO> orderByName = orderingUtils.orderByName(people);

        assertEquals(3, orderByName.size());
        assertEquals("Ana", orderByName.get(0).getName());
        assertEquals("Felipe", orderByName.get(1).getName());
        assertEquals("Joao", orderByName.get(2).getName());
    }

}