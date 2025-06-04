package com.gabrielspassos.mapper;

import com.gabrielspassos.domain.PetEntity;
import com.gabrielspassos.dto.PetDTO;

import java.util.UUID;

public final class PetMapper {

    private PetMapper() {

    }

    public static PetDTO mapEntityToDTO(PetEntity petEntity) {
        if (null == petEntity || null == petEntity.getId()) {
            throw new IllegalArgumentException("invalid pet entity to map to dto");
        }

        PetDTO petDTO = new PetDTO();
        petDTO.setId(UUID.fromString(petEntity.getId()));
        petDTO.setName(petEntity.getName());
        petDTO.setCreatedAt(petEntity.getCreatedAt());
        return petDTO;
    }
}
