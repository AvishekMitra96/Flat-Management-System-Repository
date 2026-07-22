package com.fms.controller;

import com.fms.entity.User;
import com.fms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth/internal")
@RequiredArgsConstructor
public class InternalAuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User userRequest) {
        if (userRepository.findByUsername(userRequest.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username is already taken!");
        }

        User user = new User();
        user.setUsername(userRequest.getUsername());
        user.setPassword(encoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        user.setFirstLogin(userRequest.isFirstLogin());

        userRepository.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}
