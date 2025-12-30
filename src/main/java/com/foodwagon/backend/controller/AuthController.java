package com.foodwagon.backend.controller;

import com.foodwagon.backend.service.AuthService;
import com.foodwagon.backend.dto.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService service;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest r) {
        return service.login(r);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest r) {
        return service.register(r);
    }

}
