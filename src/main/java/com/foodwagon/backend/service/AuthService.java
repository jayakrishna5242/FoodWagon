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

        boolean userExists =
                (r.email() != null && userRepository.findByEmail(r.email()).isPresent())
                        || (!r.phone().equals("0000000000")  && userRepository.findByPhone(r.phone()).isPresent());

        if (userExists) {
            return null; // user already present (either email or phone)
        }


        User user = User.builder()
                .name(r.name())
                .email(r.email())
                .phone(r.phone())
                .password(r.password()) // later: encode/hash
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

        // Try email, then phone; if not found, return null for now
        User user = userRepository.findByEmail(r.identifier())
                .orElseGet(() ->
                        userRepository.findByPhone(r.identifier())
                                .orElse(null)
                );

        if (user == null) {
            return null; // invalid credentials, no exception yet
        }

        // Plain equality check for now; later: passwordEncoder.matches(...)
        if (!user.getPassword().equals(r.password())) {
            return null; // invalid credentials
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
