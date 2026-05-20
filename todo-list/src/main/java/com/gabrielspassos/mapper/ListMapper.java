package com.gabrielspassos.mapper;

import com.gabrielspassos.controller.v1.response.ListResponse;
import com.gabrielspassos.entity.ListEntity;

public class ListMapper {

    public static ListResponse toResponse(ListEntity entity) {
        final String id = StringMapper.fromUUID(entity.getId());
        final String userId = StringMapper.fromUUID(entity.getUserId());
        final String createdAt = StringMapper.fromLocalDateTime(entity.getCreatedAt());

        return new ListResponse(id, userId, entity.getName(), createdAt);
    }

}
