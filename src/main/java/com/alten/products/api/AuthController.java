package com.alten.products.api;

import com.alten.products.api.product.LoginRequest;
import com.alten.products.domain.User;
import com.alten.products.service.JwtService;
import com.alten.products.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/account")
    public ResponseEntity<?> register(@RequestBody UserInfos user) {
        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "Compte créé avec succès", "userId", savedUser.getId()));
    }

    @PostMapping("/token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        User user = userService.findByEmail(loginRequest.email());

        Boolean a = passwordEncoder.matches(loginRequest.password(), user.getPassword());

        if (!a) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
