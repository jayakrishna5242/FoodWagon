package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.auth.AuthResponse;
import com.foodwagon.backend.dto.auth.ApiErrorResponse;
import com.foodwagon.backend.dto.partner.PartnerLoginRequest;
import com.foodwagon.backend.dto.partner.PartnerRegisterRequest;
import com.foodwagon.backend.entity.Restaurant;
import com.foodwagon.backend.service.PartnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
@CrossOrigin
public class PartnerController {

    private final PartnerService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody PartnerRegisterRequest r) {

        AuthResponse result = service.register(r);

        if (result == null) {
            ApiErrorResponse error = new ApiErrorResponse(
                    "User with this email/phone already exists",
                    409,
                    Instant.now()
            );
            return ResponseEntity.status(409).body(error);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody PartnerLoginRequest r) {

        AuthResponse result = service.login(r);

        if (result == null) {
            ApiErrorResponse error = new ApiErrorResponse(
                    "Invalid email or password",
                    401,
                    Instant.now()
            );
            return ResponseEntity.status(401).body(error);
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/{userId}/restaurant")
    public ResponseEntity<?> getRestaurant(@PathVariable Long userId) {

        Restaurant restaurant = service.getRestaurant(userId);

        if (restaurant == null) {
            ApiErrorResponse error = new ApiErrorResponse(
                    "Restaurant not found",
                    404,
                    Instant.now()
            );
            return ResponseEntity.status(404).body(error);
        }

        return ResponseEntity.ok(restaurant);
    }
}
