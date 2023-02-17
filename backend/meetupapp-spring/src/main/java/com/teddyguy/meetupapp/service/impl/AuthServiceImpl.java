package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.request.SignInRequest;
import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import com.teddyguy.meetupapp.model.MeetUpUser;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.service.AuthService;
import com.teddyguy.meetupapp.util.JwtUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    JwtUtils jwtUtils;
    PasswordEncoder passwordEncoder;
    private MeetUpUserRepository meetUpUserRepository;

    @Override
    public JwtResponse signIn(SignInRequest request) {
        UserDetails user = loadUserByUsername(request.email());
        return new JwtResponse(jwtUtils.generateToken(user));
    }

    @Override
    public void signUp(SignUpRequest request) {
        if (meetUpUserRepository.findByEmail(request.email()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }

        MeetUpUser user = MeetUpUser.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.rawPassword()))
                .name(request.name())
                .build();

        meetUpUserRepository.save(user);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return meetUpUserRepository
                .findByEmail(username)
                .orElseThrow(
                        () -> new EntityNotFoundException("Did not find user with email: " + username)
                );
    }

}
