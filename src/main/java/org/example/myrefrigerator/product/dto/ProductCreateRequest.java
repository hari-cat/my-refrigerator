package org.example.myrefrigerator.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.example.myrefrigerator.product.entity.ProductCategory;

public record ProductCreateRequest(
        @NotBlank
        String name,
        @NotNull
        ProductCategory category
) {
}
