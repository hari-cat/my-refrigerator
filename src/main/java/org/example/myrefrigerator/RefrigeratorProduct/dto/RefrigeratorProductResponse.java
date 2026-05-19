package org.example.myrefrigerator.RefrigeratorProduct.dto;

import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;

import java.time.LocalDate;

public record RefrigeratorProductResponse(
        Long id,
        String name,
        int quantity,
        LocalDate expiredAt
) {
    public static RefrigeratorProductResponse from(RefrigeratorProduct refrigeratorProduct){
        return new RefrigeratorProductResponse(refrigeratorProduct.getId(), refrigeratorProduct.getProduct().getName(), refrigeratorProduct.getQuantity(), refrigeratorProduct.getExpiredAt());
    }
}
