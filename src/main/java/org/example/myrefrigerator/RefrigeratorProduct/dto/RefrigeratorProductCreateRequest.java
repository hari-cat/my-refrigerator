package org.example.myrefrigerator.RefrigeratorProduct.dto;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record RefrigeratorProductCreateRequest(
        @NotNull
        Long productId,
        @NotNull
        int quantity,
        LocalDate expiredAt
) {
}
