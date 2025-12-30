package com.foodwagon.backend.dto.menu;

public record MenuItemRequest(
        String name,
        String description,
        Integer price,
        String imageUrl,
        Boolean isVeg,
        String category,
        Long restaurantId
) {}
