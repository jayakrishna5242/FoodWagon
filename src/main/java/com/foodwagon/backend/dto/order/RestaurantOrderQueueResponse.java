package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;
import java.time.Instant;
import java.util.List;

public record RestaurantOrderQueueResponse(
        Long id,
        String customerName,
        String customerPhone,
        List<OrderItemDTO> items,
        Double totalAmount,
        OrderStatus status,
        Instant date
) {}
