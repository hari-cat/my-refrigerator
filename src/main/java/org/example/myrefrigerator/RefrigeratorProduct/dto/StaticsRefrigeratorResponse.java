package org.example.myrefrigerator.RefrigeratorProduct.dto;

public record StaticsRefrigeratorResponse (
        int refrigeratorScore,
        long expiringProductCount,
        long expiredProductCount
){
    public static StaticsRefrigeratorResponse from (int refrigeratorScore, long expiringProductCount, long expiredProductCount){
        return new StaticsRefrigeratorResponse(refrigeratorScore, expiringProductCount, expiredProductCount);
    }
}
