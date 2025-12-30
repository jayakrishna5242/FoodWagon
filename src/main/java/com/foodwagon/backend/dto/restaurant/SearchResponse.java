package com.foodwagon.backend.dto.restaurant;

import java.util.List;

public record SearchResponse(
        List<RestaurantResponse> restaurants,
        List<SearchItemResponse> items
) {}
