package org.example.myrefrigerator.product.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.global.common.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", nullable = false)
    private ProductCategory category;

    @OneToMany(mappedBy = "product")
    private List<RefrigeratorProduct> refrigeratorProducts = new ArrayList<>();

    public Product(String name, ProductCategory category) {
        this.name = name;
        this.category = category;
    }

    public static Product create(String name, ProductCategory category) {
        return new Product(name, category);
    }

    public void changeName(String name) {
        this.name = name;
    }

    public void moveCategory(ProductCategory category){
        this.category = category;
    }
}
