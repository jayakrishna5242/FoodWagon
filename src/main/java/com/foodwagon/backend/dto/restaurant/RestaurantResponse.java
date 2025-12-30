package com.foodwagon.backend.dto.restaurant;

import java.util.List;

public record RestaurantResponse(
        Long id,
        String name,
        String imageUrl,
        Double rating,
        String deliveryTime,
        String costForTwo,
        List<String> cuisines,
        String location,
        String city
) {}
