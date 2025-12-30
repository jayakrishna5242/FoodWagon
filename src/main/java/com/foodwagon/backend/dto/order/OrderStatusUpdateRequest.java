package com.foodwagon.backend.dto.order;

import com.foodwagon.backend.enums.OrderStatus;

public record OrderStatusUpdateRequest(
        OrderStatus status
) {}
