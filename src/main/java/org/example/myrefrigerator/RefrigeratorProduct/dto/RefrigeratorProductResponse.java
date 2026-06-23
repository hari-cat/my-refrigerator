package org.example.myrefrigerator.RefrigeratorProduct.dto;

import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.product.entity.ProductCategory;

import java.time.LocalDate;

public record RefrigeratorProductResponse(
        Long id,
        String name,
        int quantity,
        LocalDate expiredAt,
        String origin,
        ProductCategory category
) {
    public static RefrigeratorProductResponse from(RefrigeratorProduct refrigeratorProduct){
        return new RefrigeratorProductResponse(refrigeratorProduct.getId(), refrigeratorProduct.getProduct().getName(), refrigeratorProduct.getQuantity(), refrigeratorProduct.getExpiredAt(), refrigeratorProduct.getOrigin(), refrigeratorProduct.getProduct().getCategory());
    }
}
