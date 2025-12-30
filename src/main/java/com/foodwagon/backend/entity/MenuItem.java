package com.foodwagon.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "menu_items")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Integer price;
    private String imageUrl;
    private Boolean isVeg;
    private String category;

    @Column(nullable = false)
    private Long restaurantId;   // FK to Restaurant

    @Builder.Default
    private Boolean inStock = true;
}
