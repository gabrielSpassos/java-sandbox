package com.gabrielspassos.patterns.creational.builder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuilderTest {

    @Test
    void shouldCreatePerson() {
        var person = PersonDTO.PersonDTOBuilder.builder()
                .withName("Gabriel")
                .withAge(29)
                .build();

        assertNotNull(person);
        assertEquals("Gabriel", person.getName());
        assertEquals(29, person.getAge());
    }

    @Test
    void shouldCreatePersonWithoutName() {
        var person = PersonDTO.PersonDTOBuilder.builder()
                .withAge(29)
                .build();

        assertNotNull(person);
        assertNull(person.getName());
        assertEquals(29, person.getAge());
    }

    @Test
    void shouldCreatePersonWithoutAge() {
        var person = PersonDTO.PersonDTOBuilder.builder()
                .withName("Gabriel")
                .build();

        assertNotNull(person);
        assertEquals("Gabriel", person.getName());
        assertNull(person.getAge());
    }

    @Test
    void shouldCreateEmptyPerson() {
        var person = PersonDTO.PersonDTOBuilder.builder().build();

        assertNotNull(person);
        assertNull(person.getName());
        assertNull(person.getAge());
    }

}