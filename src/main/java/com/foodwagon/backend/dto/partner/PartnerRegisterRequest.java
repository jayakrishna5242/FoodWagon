package com.foodwagon.backend.dto.partner;

public record PartnerRegisterRequest(
        String ownerName,
        String restaurantName,
        String email,
        String password,
        String phone,
        String city,
        String address,
        String cuisines,
        String imageUrl,
        String costForTwo
) {}
