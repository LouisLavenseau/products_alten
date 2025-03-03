package com.alten.products.dto;

public class JwtOutputDto {
    private String token;

    public JwtOutputDto(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
