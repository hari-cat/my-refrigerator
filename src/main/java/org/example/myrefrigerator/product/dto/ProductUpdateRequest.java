package org.example.myrefrigerator.product.dto;

import org.example.myrefrigerator.product.entity.ProductCategory;

public record ProductUpdateRequest(
        String name,
        ProductCategory category
) {
}
