package org.example.myrefrigerator.refrigerator.dto;

import org.example.myrefrigerator.refrigerator.entity.Refrigerator;

public record RefrigeratorResponse(
        Long id,
        String name
) {
    public static RefrigeratorResponse from(Refrigerator refrigerator){
        return new RefrigeratorResponse(refrigerator.getId(), refrigerator.getName());
    }
}
