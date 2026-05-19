package org.example.myrefrigerator.product.dto;

import jakarta.persistence.*;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.entity.ProductCategory;

public record ProductResponse(
        Long id,
        String name,
        ProductCategory category) {
    public static ProductResponse from(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getCategory());
    }
}
