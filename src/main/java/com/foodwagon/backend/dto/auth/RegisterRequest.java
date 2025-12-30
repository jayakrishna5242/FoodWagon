package com.foodwagon.backend.dto.auth;

public record RegisterRequest(
        String name,
        String phone   ,
        String email,
        String password
) {}
