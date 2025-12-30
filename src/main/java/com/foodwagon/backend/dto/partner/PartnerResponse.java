package com.foodwagon.backend.dto.partner;

public record PartnerResponse(
        Long id,
        String name,
        String email,
        String role
) {}
