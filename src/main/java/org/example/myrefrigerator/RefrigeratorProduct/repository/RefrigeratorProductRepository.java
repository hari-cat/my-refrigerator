package org.example.myrefrigerator.RefrigeratorProduct.repository;

import org.example.myrefrigerator.RefrigeratorProduct.entity.RefrigeratorProduct;
import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

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

    @Query(
            """
                        select count(*) from RefrigeratorProduct r where r.refrigerator.id = :refrigeratorId and r.status = 'ACTIVE' and r.expiredAt < CURRENT_TIMESTAMP
                    """
    )
    long countExpiredProducts(Long refrigeratorId);

    @Query(
            """
                        select count(*) from RefrigeratorProduct r where r.refrigerator.id = :refrigeratorId and r.status = 'ACTIVE' and r.expiredAt between CURRENT_TIMESTAMP and :fiveDaysLater
                    """
    )
    long countExpiringProducts(Long refrigeratorId, LocalDate fiveDaysLater);
}
