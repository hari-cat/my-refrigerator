package org.example.myrefrigerator.RefrigeratorProduct.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.myrefrigerator.RefrigeratorProduct.dto.RefrigeratorProductCreateRequest;
import org.example.myrefrigerator.global.common.BaseEntity;
import org.example.myrefrigerator.product.entity.Product;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;

import java.time.LocalDate;

@Entity
@Getter
@Table(name = "refrigerator_products", uniqueConstraints = {
        @UniqueConstraint(
                columnNames = {
                        "refrigerator_id",
                        "product_id",
                        "expired_at"
                }
        )
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RefrigeratorProduct extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "refrigerator_id")
    private Refrigerator refrigerator;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "expired_at", nullable = false)
    private LocalDate expiredAt;

    @Column(name = "origin")
    private String origin;

    public RefrigeratorProduct(Refrigerator refrigerator, Product product, int quantity, LocalDate expiredAt, String origin) {
        this.refrigerator = refrigerator;
        this.product = product;
        this.quantity = quantity;
        this.expiredAt = expiredAt;
        this.origin = origin;
    }

    public static RefrigeratorProduct create(Refrigerator refrigerator, Product product, RefrigeratorProductCreateRequest request) {
        return new RefrigeratorProduct(refrigerator, product, request.quantity(), request.expiredAt(), request.origin());

    }

    public void addQuantity(int quantity) {
        if (quantity < 0) throw new IllegalStateException("재고가 부족합니다.");

        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "RefrigeratorProduct{" +
                "id=" + id +
                ", refrigerator=" + refrigerator +
                ", product=" + product +
                ", quantity=" + quantity +
                ", expiredAt=" + expiredAt +
                ", origin='" + origin + '\'' +
                '}';
    }
}
