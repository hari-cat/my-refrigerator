package org.example.myrefrigerator.refrigerator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RefrigeratorRegisterRequest(
        @NotBlank
        @Size(max = 20)
        String name
) {
}
