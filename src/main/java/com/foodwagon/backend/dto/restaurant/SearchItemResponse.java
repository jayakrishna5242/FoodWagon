package com.foodwagon.backend.dto.restaurant;

public record SearchItemResponse(
        Long id,
        String name,
        String description,
        Integer price,
        String imageUrl,
        Boolean isVeg,
        String category,
        Long restaurantId,
        String restaurantName,
        Boolean inStock
) {}
