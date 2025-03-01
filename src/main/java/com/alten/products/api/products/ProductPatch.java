package com.alten.products.api.products;

import com.alten.products.domain.InventoryStatus;

public record ProductPatch(
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
