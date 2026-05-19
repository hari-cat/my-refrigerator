package org.example.myrefrigerator.RefrigeratorProduct.dto;

import jakarta.validation.constraints.NotNull;
import org.example.myrefrigerator.product.entity.ProductCategory;

public record RefrigeratorProductSearchCondition(
        @NotNull
        Long refrigeratorId,
        String name,
        ProductCategory category
) {
}
