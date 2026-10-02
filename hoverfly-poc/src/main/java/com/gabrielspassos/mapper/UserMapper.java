package com.gabrielspassos.mapper;

import com.gabrielspassos.controller.v1.response.UserResponse;
import com.gabrielspassos.entity.UserEntity;

public final class UserMapper {

    private UserMapper() {
    }

    public static UserResponse toResponse(UserEntity entity) {
        final String id = StringMapper.fromUUID(entity.getId());
        final String createdAt = StringMapper.fromLocalDateTime(entity.getCreatedAt());

        return new UserResponse(id, entity.getName(), createdAt);
    }

}
