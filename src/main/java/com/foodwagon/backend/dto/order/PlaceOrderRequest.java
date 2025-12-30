package com.foodwagon.backend.dto.order;

import java.util.List;

public record PlaceOrderRequest(
        Long userId,
        Long restaurantId,
        List<OrderItemDTO> items,
        Double totalAmount,
        String deliveryAddress,
        String paymentMethod
) {}
