package com.gabrielspassos.controller.v1.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateItemRequest(@NotBlank(message = "should have status to update item") String status) {
}
