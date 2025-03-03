package com.alten.products.api.product;

public record LoginRequest(
    String email,
    String password
) { }
