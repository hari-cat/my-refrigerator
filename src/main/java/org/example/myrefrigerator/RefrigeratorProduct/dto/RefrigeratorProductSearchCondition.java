package org.example.myrefrigerator.RefrigeratorProduct.dto;

import org.example.myrefrigerator.product.entity.ProductCategory;

public record RefrigeratorProductSearchCondition(
        String name,
        ProductCategory category
) {
}
