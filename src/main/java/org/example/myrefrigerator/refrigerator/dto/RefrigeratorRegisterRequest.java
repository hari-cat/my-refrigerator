package org.example.myrefrigerator.refrigerator.dto;

import jakarta.validation.constraints.NotNull;

public record RefrigeratorRegisterRequest(
        @NotNull
        Long ownerId,
        String name
) {
}
