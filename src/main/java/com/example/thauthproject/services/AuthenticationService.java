package com.example.thauthproject.services;

import com.example.thauthproject.dto.requests.AuthenticationRequest;
import com.example.thauthproject.dto.requests.RegisterRequest;
import com.example.thauthproject.dto.responses.AuthenticationResponse;
import com.example.thauthproject.enums.Role;
import com.example.thauthproject.models.User;
import com.example.thauthproject.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AuthenticationService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {

        var user = User.builder()
                .email(registerRequest.email())
                .login(registerRequest.login())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                   authenticationRequest.login(),
                   authenticationRequest.password()
                )
        );


        var user = userRepository.findByLogin(authenticationRequest.login())
                .orElseThrow();
        System.out.println(user);
        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
