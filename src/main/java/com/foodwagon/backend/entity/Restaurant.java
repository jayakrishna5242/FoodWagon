package com.foodwagon.backend.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "restaurants")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String city;

    // ✅ THIS MUST BE location
    private String location;

    private String cuisines;
    private String imageUrl;
    private String costForTwo;

    @Builder.Default
    private Double rating = 4.2;

    @Builder.Default
    private String deliveryTime = "30-35 mins";

    private Long ownerId;
}
