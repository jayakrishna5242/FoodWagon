package com.foodwagon.backend.service;


import com.foodwagon.backend.dto.order.*;
import com.foodwagon.backend.entity.Order;
import com.foodwagon.backend.entity.OrderItem;
import com.foodwagon.backend.enums.OrderStatus;
import com.foodwagon.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    /* ---------------- PLACE ORDER ---------------- */
    public OrderResponse placeOrder(CreateOrderRequest request) {

        Order order = Order.builder()
                .userId(request.userId())
                .restaurantId(request.restaurantId())
                .restaurantName("The Sizzling Grill") // later fetch dynamically
                .totalAmount(request.totalAmount())
                .deliveryAddress(request.deliveryAddress())
                .paymentMethod(request.paymentMethod())
                .status(OrderStatus.PENDING)
                .createdAt(Instant.now())
                .build();

        List<OrderItem> items = request.items().stream()
                .map(i -> OrderItem.builder()
                        .menuItemId(i.id())
                        .name(i.name())
                        .price(i.price())
                        .quantity(i.quantity())
                        .order(order)
                        .build())
                .toList();

        order.setItems(items);
        Order saved = orderRepository.save(order);

        return mapToOrderResponse(saved);
    }

    /* ---------------- USER ORDERS ---------------- */
    public List<CustomerOrderHistoryResponse> getUserOrders(Long userId) {
        return orderRepository.findByUserId(userId)
                .stream()
                .map(this::mapToCustomerHistory)
                .toList();
    }

    /* ---------------- RESTAURANT QUEUE ---------------- */
    public List<RestaurantOrderQueueResponse> getRestaurantOrders(Long restaurantId) {
        return orderRepository
                .findByRestaurantIdAndStatusNot(restaurantId, OrderStatus.DELIVERED)
                .stream()
                .map(this::mapToRestaurantQueue)
                .toList();
    }

    /* ---------------- UPDATE STATUS ---------------- */
    public OrderStatusUpdateRequest updateStatus(Long orderId, OrderStatusUpdateRequest request) {
        Order order = orderRepository.findById(orderId).orElseThrow();
        order.setStatus(request.status());
        orderRepository.save(order);
        return request;
    }

    /* ---------------- MAPPERS ---------------- */
    private OrderResponse mapToOrderResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getUserId(),
                order.getRestaurantId(),
                order.getRestaurantName(),
                mapItems(order),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getDeliveryAddress()
        );
    }

    private CustomerOrderHistoryResponse mapToCustomerHistory(Order order) {
        return new CustomerOrderHistoryResponse(
                order.getId(),
                order.getRestaurantName(),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                mapItems(order)
        );
    }

    private RestaurantOrderQueueResponse mapToRestaurantQueue(Order order) {
        return new RestaurantOrderQueueResponse(
                order.getId(),
                "John Doe",
                "9876543210",
                mapItems(order),
                order.getTotalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }

    private List<OrderItemDTO> mapItems(Order order) {
        return order.getItems().stream()
                .map(i -> new OrderItemDTO(
                        i.getMenuItemId(),
                        i.getName(),
                        i.getQuantity(),
                        i.getPrice()
                ))
                .toList();
    }
}
