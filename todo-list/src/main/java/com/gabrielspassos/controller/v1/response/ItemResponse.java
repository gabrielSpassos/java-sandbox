package com.gabrielspassos.controller.v1.response;

public record ItemResponse(String id,
                           String listId,
                           String status,
                           String description,
                           String createdAt,
                           String updatedAt) {
}
