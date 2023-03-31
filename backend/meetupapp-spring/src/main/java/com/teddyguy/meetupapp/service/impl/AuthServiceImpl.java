package com.teddyguy.meetupapp.service.impl;

import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import com.teddyguy.meetupapp.model.MeetUpUser;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.service.AuthService;
import com.teddyguy.meetupapp.util.JwtUtils;
import jakarta.persistence.EntityExistsException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    JwtUtils jwtUtils;
    PasswordEncoder passwordEncoder;
    private MeetUpUserRepository meetUpUserRepository;

    @Override
    public JwtResponse signIn(String email) {
        MeetUpUser user = meetUpUserRepository.findByEmail(email)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Did not find user with email: " + email)
                );
        return new JwtResponse(jwtUtils.generateTokenMeetUpUser(user));
    }

    @Override
    public void signUp(SignUpRequest request) {
        if (meetUpUserRepository.findByEmail(request.email()).isPresent()) {
            throw new EntityExistsException();
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
                        () -> new UsernameNotFoundException("Did not find user with email: " + username)
                );
    }

}
