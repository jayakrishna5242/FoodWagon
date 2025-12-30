package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;
import java.time.Instant;
import java.util.List;

public record CustomerOrderHistoryResponse(
        Long id,
        String restaurantName,
        Double totalAmount,
        OrderStatus status,
        Instant date,
        List<OrderItemDTO> items
) {}
