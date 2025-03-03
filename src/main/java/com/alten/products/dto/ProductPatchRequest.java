package com.alten.products.dto;

import com.alten.products.domain.InventoryStatus;

public record ProductPatchRequest(
        String code,
        String name,
        String description,
        String image,
        String category,
        Float price,
        Integer quantity,
        String internalReference,
        Long shellId,
        InventoryStatus inventoryStatus,
        Float rating
) { }
