package org.example.myrefrigerator.product.repository;

import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.product.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findProductByStatus(Status status);
    boolean existsByNameAndCategory(String name, ProductCategory category);
}
