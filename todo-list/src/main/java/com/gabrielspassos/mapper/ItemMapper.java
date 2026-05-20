package com.gabrielspassos.mapper;

import com.gabrielspassos.controller.response.ItemResponse;
import com.gabrielspassos.entity.ItemEntity;

public class ItemMapper {

    public static ItemResponse toResponse(ItemEntity entity) {
        final String id = StringMapper.fromUUID(entity.getId());
        final String listId = StringMapper.fromUUID(entity.getListId());
        final String status = StringMapper.fromItemStatus(entity.getStatus());
        final String createdAt = StringMapper.fromLocalDateTime(entity.getCreatedAt());
        final String updatedAt = StringMapper.fromLocalDateTime(entity.getUpdatedAt());

        return new ItemResponse(id, listId, status, entity.getDescription(), createdAt, updatedAt);
    }

}
