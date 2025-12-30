package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.auth.*;
import com.foodwagon.backend.entity.User;
import com.foodwagon.backend.enums.UserRole;
import com.foodwagon.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    /* -------- REGISTER -------- */
    public AuthResponse register(RegisterRequest r) {

        userRepository.findByEmail(r.email())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already exists");
                });
        if (r.phone() != null) {
            userRepository.findByPhone(r.phone())
                    .ifPresent(u -> {
                        throw new RuntimeException("Phone already exists");
                    });
        }
        User user = User.builder()
                .name(r.name())
                .email(r.email())
                .phone(r.phone())
                .password(r.password())
                .role(UserRole.CUSTOMER)
                .build();

        User saved = userRepository.save(user);

        UserResponse userResponse = new UserResponse(
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole().name()
        );

        return new AuthResponse(userResponse);
    }

    /* -------- LOGIN -------- */
    public AuthResponse login(LoginRequest r) {

        User user = userRepository.findByEmail(r.identifier())
                .orElseGet(() ->
                        userRepository.findByPhone(r.identifier())
                                .orElseThrow(() -> new RuntimeException("Invalid credentials"))
                );

        if (!user.getPassword().equals(r.password())) {
            throw new RuntimeException("Invalid credentials");
        }

        UserResponse userResponse = new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole().name()
        );

        return new AuthResponse(userResponse);
    }

}
