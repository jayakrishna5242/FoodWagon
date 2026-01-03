package com.foodwagon.backend.entity;

import com.foodwagon.backend.enums.UserRole;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;
    @Column()
    private String phone;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
