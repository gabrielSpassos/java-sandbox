package com.gabrielspassos.mapper;

import com.gabrielspassos.exception.BadRequestException;

import java.util.UUID;

public class UUIDMapper {

    public static UUID toUUID(String uuidAsString) {
        try {
            return UUID.fromString(uuidAsString);
        } catch (Exception e) {
            throw new BadRequestException("invalid id", "INVALID_ID");
        }
    }

}
