package com.fms.controller;

import com.fms.dto.JwtResponse;
import com.fms.dto.LoginRequest;
import com.fms.dto.PasswordResetRequest;
import com.fms.entity.User;
import com.fms.repository.UserRepository;
import com.fms.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtTokenProvider.generateToken(authentication);

        User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();

        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getRole().name(),
                user.isFirstLogin()));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest request) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        if (currentUsername == null || currentUsername.equals("anonymousUser")) {
            return ResponseEntity.status(401).body("Unauthorized");
        }

        User user = userRepository.findByUsername(currentUsername).orElseThrow();
        
        if (!encoder.matches(request.getOldPassword(), user.getPassword())) {
            return ResponseEntity.badRequest().body("Incorrect old password");
        }

        user.setPassword(encoder.encode(request.getNewPassword()));
        user.setFirstLogin(false);
        userRepository.save(user);

        return ResponseEntity.ok("Password reset successfully");
    }
}
