package com.foodwagon.backend.entity;

import com.foodwagon.backend.entity.OrderItem;
import com.foodwagon.backend.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;
    private Long restaurantId;

    private String restaurantName;
    private String restaurantImageUrl;

    private Double totalAmount;

    private String deliveryAddress;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(length = 30, nullable = false)
    private OrderStatus status;

    private Instant createdAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
