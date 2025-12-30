package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;
import java.time.Instant;
import java.util.List;

public record OrderResponse(
        Long id,
        Long userId,
        Long restaurantId,
        String restaurantName,
        List<OrderItemDTO> items,
        Double totalAmount,
        OrderStatus status,
        Instant date,
        String deliveryAddress
) {}
