package org.example.myrefrigerator.product.repository;

import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    boolean existsByNameAndCategory(String name, ProductCategory category);
}
