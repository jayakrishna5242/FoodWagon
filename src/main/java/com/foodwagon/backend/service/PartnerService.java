package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.auth.AuthResponse;
import com.foodwagon.backend.dto.auth.UserResponse;
import com.foodwagon.backend.dto.partner.*;
import com.foodwagon.backend.entity.Restaurant;
import com.foodwagon.backend.entity.User;
import com.foodwagon.backend.enums.UserRole;
import com.foodwagon.backend.repository.RestaurantRepository;
import com.foodwagon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PartnerService {

    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    /* -------- REGISTER PARTNER -------- */
    public AuthResponse register(PartnerRegisterRequest r) {

        // Email already exists -> fail quietly for now
        if (userRepository.findByEmail(r.email()).isPresent()) {
            return null; // later: throw custom exception / return proper error DTO
        }

        User partner = User.builder()
                .name(r.ownerName())
                .email(r.email())
                .phone(r.phone())
                .password(r.password()) // later: encode/hash
                .role(UserRole.PARTNER)
                .build();

        User savedUser = userRepository.save(partner);

        Restaurant restaurant = Restaurant.builder()
                .name(r.restaurantName())
                .city(r.city())
                .location(r.address())
                .cuisines(r.cuisines())
                .imageUrl(r.imageUrl())
                .costForTwo(r.costForTwo())
                .ownerId(savedUser.getId())
                .build();

        restaurantRepository.save(restaurant);

        UserResponse userResponse = new UserResponse(
                savedUser.getId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getRole().name()
        );

        return new AuthResponse(userResponse);
    }

    /* -------- LOGIN PARTNER -------- */
    public AuthResponse login(PartnerLoginRequest r) {

        User user = userRepository.findByEmail(r.email()).orElse(null);

        // No user or wrong password/role -> fail quietly for now
        if (user == null
                || !user.getPassword().equals(r.password())
                || user.getRole() != UserRole.PARTNER) {
            return null; // later: custom exception + proper HTTP status
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(userResponse);
    }

    /* -------- GET PARTNER RESTAURANT -------- */
    public Restaurant getRestaurant(Long userId) {

        // If not found, just return null for now
        return restaurantRepository.findByOwnerId(userId).orElse(null);
    }
}
