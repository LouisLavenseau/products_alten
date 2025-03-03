package com.alten.products.api;

import com.alten.products.dto.LoginRequest;
import com.alten.products.dto.RegisterUserRequest;
import com.alten.products.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/account")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest user) {
        return authService.register(user);
    }

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest request) {
        return authService.authenticateUser(request);
    }
}
