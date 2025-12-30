package com.foodwagon.backend.service;

import com.foodwagon.backend.dto.menu.MenuItemRequest;
import com.foodwagon.backend.entity.MenuItem;
import com.foodwagon.backend.repository.MenuItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MenuItemService {

    private final MenuItemRepository repo;

    /* -------- ADD ITEM -------- */
    public MenuItem add(MenuItemRequest r) {

        if (r.price() <= 0) {
            throw new RuntimeException("Price must be positive");
        }

        MenuItem item = MenuItem.builder()
                .name(r.name())
                .description(r.description())
                .price(r.price())
                .imageUrl(r.imageUrl())
                .isVeg(r.isVeg())
                .category(r.category())
                .restaurantId(r.restaurantId())
                .inStock(true)
                .build();

        return repo.save(item);
    }

    /* -------- TOGGLE STOCK -------- */
    public MenuItem toggleStock(Long id) {

        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        item.setInStock(!item.getInStock());

        // ✅ Return FULL object
        return repo.save(item);
    }


    /* -------- UPDATE ITEM -------- */
    public MenuItem update(Long id, MenuItemRequest r) {

        MenuItem item = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Menu item not found"));

        item.setName(r.name());
        item.setDescription(r.description());
        item.setPrice(r.price());
        item.setImageUrl(r.imageUrl());
        item.setIsVeg(r.isVeg());
        item.setCategory(r.category());

        return repo.save(item);
    }
}
