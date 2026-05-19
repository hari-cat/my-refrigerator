package org.example.myrefrigerator.refrigerator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RefrigeratorRegisterRequest(
        @NotNull
        Long ownerId,
        @NotBlank
        @Size(max = 20)
        String name
) {
}
