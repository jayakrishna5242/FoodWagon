package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.order.*;
import com.foodwagon.backend.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@CrossOrigin
public class OrderController {

    private final OrderService orderService;

    /* PLACE ORDER */
    @PostMapping
    public OrderResponse placeOrder(@RequestBody CreateOrderRequest request) {
        return orderService.placeOrder(request);
    }

    /* USER ORDER HISTORY */
    @GetMapping("/user/{userId}")
    public List<CustomerOrderHistoryResponse> getUserOrders(@PathVariable Long userId) {
        return orderService.getUserOrders(userId);
    }

    /* RESTAURANT ORDER QUEUE */
    @GetMapping("/restaurant/{restaurantId}")
    public List<RestaurantOrderQueueResponse> getRestaurantOrders(@PathVariable Long restaurantId) {
        return orderService.getRestaurantOrders(restaurantId);
    }

    /* UPDATE STATUS */
    @PatchMapping("/{orderId}/status")
    public OrderStatusUpdateRequest updateStatus(
            @PathVariable Long orderId,
            @RequestBody OrderStatusUpdateRequest request
    ) {
        return orderService.updateStatus(orderId, request);
    }
}
