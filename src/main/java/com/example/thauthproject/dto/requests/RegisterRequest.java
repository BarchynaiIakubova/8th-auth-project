package com.example.thauthproject.dto.requests;

public record RegisterRequest(

        String email,

        String login,

        String password
) {
}
