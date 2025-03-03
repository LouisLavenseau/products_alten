package com.alten.products.dto;

public record LoginRequest(
    String email,
    String password
) { }
