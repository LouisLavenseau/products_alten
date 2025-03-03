package com.alten.products.dto;

public record RegisterUserRequest(
    String username,
    String firstname,
    String email,
    String password
) { }

