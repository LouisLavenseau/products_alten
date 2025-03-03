package com.alten.products.service;

import com.alten.products.domain.User;
import com.alten.products.dto.*;
import com.alten.products.exception.ConflictException;
import com.alten.products.exception.NotFoundException;
import com.alten.products.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import java.util.Map;

@Service
public class AuthService {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> register(RegisterUserRequest request) {
        if (userRepository.existsByEmail(request.email())) {
            throw new ConflictException("Email " + request.email() + " already used");
        }

        User user = new User(
                request.username(),
                request.firstname(),
                request.email(),
                passwordEncoder.encode(request.password())
        );

        userRepository.save(user);

        return ResponseEntity.ok(Map.of("message", "Account created with success", "userId", user.getId()));
    }

    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        User user = findByEmailSafe(loginRequest.email());

        boolean passwordCorrect = passwordEncoder.matches(loginRequest.password(), user.getPassword());

        if (!passwordCorrect) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new JwtOutputDto(token));
    }

    private User findByEmailSafe(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("User with email " + email + " not found"));
    }
}