package com.gabrielspassos.controller.v1.request;

import jakarta.validation.constraints.NotBlank;

public record UserRequest(@NotBlank(message = "user must have name") String name) {
}
