package com.aressa.oktaraserver.auth.service;

import com.aressa.oktaraserver.auth.dto.*;
import com.aressa.oktaraserver.role.model.Role;
import com.aressa.oktaraserver.security.JwtService;
import com.aressa.oktaraserver.user.model.User;
import com.aressa.oktaraserver.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        var user = User.builder()
                .userName(request.getUserName())
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.builder().id(request.getRole()).build())
                .createdAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .token(jwt)
                .build();
    }

    public AuthResponse login(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );

        var user = userRepository.findByUserName(request.getUserName())
                .orElseThrow();
        var jwt = jwtService.generateToken(user);

        return AuthResponse.builder()
                .token(jwt)
                .build();
    }
}
