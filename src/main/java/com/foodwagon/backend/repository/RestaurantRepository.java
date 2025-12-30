package com.foodwagon.backend.repository;

import com.foodwagon.backend.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    List<Restaurant> findByCityIgnoreCase(String city);

    Optional<Restaurant> findByOwnerId(Long ownerId);

    List<Restaurant> findByNameContainingIgnoreCase(String keyword);
}
