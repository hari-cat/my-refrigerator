package org.example.myrefrigerator.refrigerator.repository;

import org.example.myrefrigerator.global.dto.Status;
import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository <Refrigerator, Long> {
    boolean existsRefrigeratorsByOwnerIdAndStatus(Long ownerId, Status status);
    @Query("""
                SELECT r FROM Refrigerator r WHERE r.ownerId = :ownerId AND r.status = "ACTIVE"
            """)
    Optional<Refrigerator> findActiveRefrigerator(Long ownerId);

}
