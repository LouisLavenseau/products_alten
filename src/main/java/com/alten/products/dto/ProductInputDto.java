package com.alten.products.dto;

import com.alten.products.domain.InventoryStatus;

public record ProductInputDto(
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
