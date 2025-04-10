package com.aressa.oktaraserver.auth.controller;

import com.aressa.oktaraserver.auth.dto.AuthRequest;
import com.aressa.oktaraserver.auth.dto.AuthResponse;
import com.aressa.oktaraserver.auth.dto.RegisterRequest;
import com.aressa.oktaraserver.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}


