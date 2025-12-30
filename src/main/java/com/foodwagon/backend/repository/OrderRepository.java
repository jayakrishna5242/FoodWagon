package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.Order;
import com.foodwagon.backend.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByUserId(Long userId);

    List<Order> findByRestaurantIdAndStatusNot(
            Long restaurantId,
            OrderStatus status
    );
}
