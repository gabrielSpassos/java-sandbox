package com.gabrielspassos.controller.response;

public record ItemResponse(String id,
                           String listId,
                           String status,
                           String description,
                           String createdAt,
                           String updatedAt) {
}
