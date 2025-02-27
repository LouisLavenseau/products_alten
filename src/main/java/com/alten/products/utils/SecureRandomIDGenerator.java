package com.alten.products.utils;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class SecureRandomIDGenerator {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static long generateID() {
        return secureRandom.nextLong() & Long.MAX_VALUE;
    }
}