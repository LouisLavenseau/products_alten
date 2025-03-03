package com.alten.products.service;

import com.alten.products.api.UserInfos;
import com.alten.products.domain.User;
import com.alten.products.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User registerUser(UserInfos userInfos) {
        User user = new User(
                userInfos.username(),
                userInfos.firstname(),
                userInfos.email(),
                passwordEncoder.encode(userInfos.password())
        );
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}

