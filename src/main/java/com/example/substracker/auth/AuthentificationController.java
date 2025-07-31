package com.example.substracker.auth;

import com.example.substracker.config.JwtService;
import com.example.substracker.model.Role;
import com.example.substracker.model.User;
import com.example.substracker.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthentificationController {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final AuthentificationService service;
    @PostMapping("/register")
    public ResponseEntity<AuthentificationResponse> register(
            @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> Authenticate(
            @RequestBody AuthentificationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/auth/demo")
    public ResponseEntity<?> createDemoUser() {
        String random = UUID.randomUUID().toString().substring(0, 8);
        String email = "demo_" + random + "@test.com";
        String password = "demo";

        User demoUser = new User();
        demoUser.setEmail(email);
        demoUser.setPassword(passwordEncoder.encode(password));
        demoUser.setRole(Role.USER);
        userRepository.save(demoUser);

        String token = jwtService.generateToken(demoUser);
        return ResponseEntity.ok(Map.of("token", token));
    }




}
