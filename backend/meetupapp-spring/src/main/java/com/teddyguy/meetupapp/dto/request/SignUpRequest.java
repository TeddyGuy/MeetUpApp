package com.teddyguy.meetupapp.dto.request;

public record SignUpRequest(String email, String name, String rawPassword) {
}
