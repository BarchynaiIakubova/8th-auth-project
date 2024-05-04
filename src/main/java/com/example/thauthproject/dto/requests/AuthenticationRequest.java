package com.example.thauthproject.dto.requests;

public record AuthenticationRequest(

        String login,

        String password
) {
}
