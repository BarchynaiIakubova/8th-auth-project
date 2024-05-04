package com.example.thauthproject.controllers;

import com.example.thauthproject.dto.requests.AuthenticationRequest;
import com.example.thauthproject.dto.requests.RegisterRequest;
import com.example.thauthproject.dto.responses.AuthenticationResponse;
import com.example.thauthproject.services.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*", originPatterns = "*", maxAge = 5000, exposedHeaders = "Access-Control-Allow-Origin", allowedHeaders = "*")
@Tag(name = "Auth API", description = "Authentication and authorization endpoints")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @Operation(summary = "Register an user", description = "This method registers new users")
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest registerRequest) {

        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @Operation(summary = "Authenticate an user", description = "This method authenticates the user")
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }
}
