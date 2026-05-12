package org.example.myrefrigerator.refrigerator.repository;

import org.example.myrefrigerator.refrigerator.entity.Refrigerator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefrigeratorRepository extends JpaRepository <Refrigerator, Long> {
    boolean existsRefrigeratorsByOwnerId(Long ownerId);
}
