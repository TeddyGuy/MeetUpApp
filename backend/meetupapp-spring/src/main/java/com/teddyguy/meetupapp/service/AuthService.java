package com.teddyguy.meetupapp.service;

import com.teddyguy.meetupapp.dto.request.SignInRequest;
import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthService extends UserDetailsService {
    JwtResponse signIn(SignInRequest request);
    void signUp(SignUpRequest request);

}
