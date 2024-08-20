package com.gabrielspassos.mapper;

import com.gabrielspassos.domain.PersonEntity;
import com.gabrielspassos.dto.PersonDTO;

import java.util.UUID;

public final class PersonMapper {

    private PersonMapper() {

    }

    public static PersonDTO mapEntityToDTO(PersonEntity personEntity) {
        if (null == personEntity || null == personEntity.getUuid()) {
            throw new IllegalArgumentException("invalid person entity to map to dto");
        }

        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(personEntity.getId());
        personDTO.setUuid(UUID.fromString(personEntity.getUuid()));
        personDTO.setFirstName(personEntity.getFirstName());
        personDTO.setLastName(personEntity.getLastName());
        personDTO.setCreatedAt(personEntity.getCreatedAt());
        return personDTO;
    }

}
