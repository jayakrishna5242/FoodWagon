package com.foodwagon.backend.dto.order;

public record OrderItemDTO(
        Long id,
        String name,
        Integer quantity,
        Double price
) {}
