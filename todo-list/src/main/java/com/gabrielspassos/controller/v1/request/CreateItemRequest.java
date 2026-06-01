package com.gabrielspassos.controller.v1.request;

import jakarta.validation.constraints.NotBlank;

public record CreateItemRequest(@NotBlank(message = "item must have a description") String description) {
}
