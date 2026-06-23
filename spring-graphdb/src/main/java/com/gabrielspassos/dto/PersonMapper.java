package com.gabrielspassos.dto;

import com.gabrielspassos.domain.Person;

public final class PersonMapper {

    private PersonMapper() {
    }

    public static PersonDto toDto(Person person) {
        return new PersonDto(
                person.getId(),
                person.getName(),
                person.getAge()
        );
    }

}