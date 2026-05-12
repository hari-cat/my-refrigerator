package org.example.myrefrigerator.refrigerator.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "refrigerator")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Refrigerator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @Builder
    private Refrigerator(String name, Long ownerId) {
        this.name = name;
        this.ownerId = ownerId;
    }
}
