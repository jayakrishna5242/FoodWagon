package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;
import java.time.Instant;
import java.util.List;

public record CustomerOrderHistoryResponse(
        Long id,
        String restaurantName,
        String restaurantImageUrl,
        Double totalAmount,
        OrderStatus status,
        Instant date,
        List<OrderItemDTO> items
) {}
