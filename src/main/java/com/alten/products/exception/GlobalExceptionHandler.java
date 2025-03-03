package com.alten.products.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(NotFoundException e) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "404");
        response.put("error", "Not Found");
        response.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Map<String, String>> handleConflict(ConflictException e) {
        Map<String, String> response = new HashMap<>();
        response.put("status", "409");
        response.put("error", "Conflict");
        response.put("message", e.getMessage());

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}

