package org.example.myrefrigerator.RefrigeratorProduct.repository;

import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface RefrigeratorProductRepository extends JpaRepository<RefrigeratorProduct, Long>, RefrigeratorProductCustom {
    Optional<RefrigeratorProduct>
    findByRefrigeratorIdAndProductIdAndExpiredAt(
            Long refrigeratorId,
            Long productId,
            LocalDate expiredAt
    );

    Optional<RefrigeratorProduct> findRefrigeratorProductByIdAndRefrigeratorAndStatus(Long id, Refrigerator refrigerator, Status status);
}
