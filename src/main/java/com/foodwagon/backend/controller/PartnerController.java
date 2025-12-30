package com.foodwagon.backend.controller;

import com.foodwagon.backend.dto.auth.AuthResponse;
import com.foodwagon.backend.dto.partner.PartnerLoginRequest;
import com.foodwagon.backend.dto.partner.PartnerRegisterRequest;
import com.foodwagon.backend.service.PartnerService;
import com.foodwagon.backend.entity.Restaurant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
@RequiredArgsConstructor
@CrossOrigin
public class PartnerController {

    private final PartnerService service;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody PartnerRegisterRequest r) {
        return service.register(r);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody PartnerLoginRequest r) {
        return service.login(r);
    }

    @GetMapping("/{userId}/restaurant")
    public Restaurant getRestaurant(@PathVariable Long userId) {
        return service.getRestaurant(userId);
    }
}
