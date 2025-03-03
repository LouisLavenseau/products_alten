package com.alten.products.api.product;

import com.alten.products.domain.InventoryStatus;

public record ProductInput (
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
    float rating
) { }
