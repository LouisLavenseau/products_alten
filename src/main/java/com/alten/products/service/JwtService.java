package com.alten.products.service;

import com.alten.products.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    private static final SecretKey secretKey = Keys.hmacShaKeyFor("your_secret_key_your_secret_key_your_qz28Ygdg9287hdbhqzjh373648ghfdèIOS97290347903240392jzioehEFHIOUSEFHUISEhf".getBytes());

    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Expiration dans 1h
                .signWith(SignatureAlgorithm.HS512, secretKey) // Utilisation correcte avec jjwt 0.11+
                .compact();
    }

    public static Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey) // Utilisation correcte de jjwt 0.12+
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, User user) {
        return (user.getEmail().equals(extractUsername(token)) && !isTokenExpired(token));
    }
}

