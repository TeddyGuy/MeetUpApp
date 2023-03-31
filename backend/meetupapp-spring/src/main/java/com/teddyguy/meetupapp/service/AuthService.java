package com.teddyguy.meetupapp.service;

import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    JwtResponse signIn(String email);
    void signUp(SignUpRequest request);

}
