package com.teddyguy.meetupapp.service.impl;


import com.teddyguy.meetupapp.dto.request.SignInRequest;
import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import com.teddyguy.meetupapp.model.MeetUpUser;
import com.teddyguy.meetupapp.repository.MeetUpUserRepository;
import com.teddyguy.meetupapp.util.JwtUtils;
import jakarta.persistence.EntityExistsException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private JwtUtils jwtUtils;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private MeetUpUserRepository meetUpUserRepository;
    @InjectMocks
    private AuthServiceImpl authService;
    static MeetUpUser testMeetUpUser;

    @BeforeAll
    static void setup(){
        testMeetUpUser = MeetUpUser
                .builder()
                .email("test@meetupapp.com")
                .password("testPass123")
                .build();
    }
    @Test
    void signInHappyDay(){
        //Arrange

        Mockito.when(meetUpUserRepository.findByEmail(anyString())).thenReturn(Optional.of(testMeetUpUser));
        Mockito.when(jwtUtils.generateToken(any(UserDetails.class))).thenReturn("jwt");

        //Act & Assert

        JwtResponse jwtResponse = authService.signIn(testMeetUpUser.getEmail());

        Assertions.assertThat(jwtResponse).isNotNull();
        Assertions.assertThat(jwtResponse.jwt()).isNotNull();
    }

    @Test
    void signInUsernameNotFound(){

        //Act & Assert

        Assertions.assertThatThrownBy(() -> authService.signIn("test@email.com"))
                .isInstanceOf(UsernameNotFoundException.class);
    }


    @Test
    void signUpHappyDay(){

        //Act

        authService.signUp(new SignUpRequest("email","name","password"));

        //Assert

        Mockito.verify(meetUpUserRepository).save(any(MeetUpUser.class));
        Mockito.verify(passwordEncoder).encode(anyString());
    }

    @Test
    void signUpEmailTaken(){
        //Arrange

        Mockito.when(meetUpUserRepository.findByEmail(anyString())).thenReturn(Optional.of(Mockito.mock(MeetUpUser.class)));

        //Act & Assert

        Assertions.assertThatThrownBy(() -> authService.signUp(new SignUpRequest("email","name","password")))
                .isInstanceOf(EntityExistsException.class);
    }
}
