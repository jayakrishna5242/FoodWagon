package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.restaurant.RestaurantResponse;
import com.foodwagon.backend.dto.restaurant.SearchResponse;
import com.foodwagon.backend.entity.MenuItem;
import com.foodwagon.backend.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
@CrossOrigin
public class RestaurantController {

    private final RestaurantService restaurantService;

    /* -------- GET RESTAURANTS -------- */
    @GetMapping
    public List<RestaurantResponse> getRestaurants(
            @RequestParam(required = false) String city
    ) {
        return restaurantService.getRestaurants(city);
    }

    /* -------- GET MENU -------- */
    @GetMapping("/{restaurantId}/menu")
    public List<MenuItem> getMenu(@PathVariable Long restaurantId) {
        return restaurantService.getMenu(restaurantId);
    }

    /* -------- SEARCH -------- */
    @GetMapping("/search")
    public SearchResponse search(@RequestParam String q) {
        return restaurantService.search(q);
    }
}
