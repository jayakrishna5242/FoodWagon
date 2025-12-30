package com.foodwagon.backend.dto.partner;

public record PartnerLoginRequest(
        String email,
        String phone,
        String password
) {}
