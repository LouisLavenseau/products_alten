package com.alten.products.dto;

import com.alten.products.domain.InventoryStatus;

public record ProductOutputDto(
        long id,
        String code,
        String name,
        String description,
        String image,
        String category,
        float price,
        int quantity,
        String internalReference,
        long shellId,
        InventoryStatus inventoryStatus,
        float rating,
        float createdAt,
        float updatedAt
) { }
