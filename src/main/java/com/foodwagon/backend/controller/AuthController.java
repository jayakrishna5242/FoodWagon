package com.foodwagon.backend.controller;

import com.foodwagon.backend.service.AuthService;
import com.foodwagon.backend.dto.auth.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {

        AuthResponse result = authService.register(request);

        if (result == null) {
            ApiErrorResponse error = new ApiErrorResponse(
                    "User with this email / phone already exists",
                    409,
                    Instant.now()
            );
            return ResponseEntity.status(409).body(error);
        }

        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        AuthResponse result = authService.login(request);

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
}

