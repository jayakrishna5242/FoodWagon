package com.foodwagon.backend.dto.auth;

public record LoginRequest(
        String identifier,
        String password
) {}
