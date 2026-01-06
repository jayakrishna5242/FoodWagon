package com.foodwagon.backend.dto.auth;

import java.time.Instant;

public record ApiErrorResponse(
        String message,
        int status,
        Instant timestamp
) {}
