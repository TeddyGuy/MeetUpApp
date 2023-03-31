package com.teddyguy.meetupapp.controller;

import com.teddyguy.meetupapp.dto.request.SignInRequest;
import com.teddyguy.meetupapp.dto.request.SignUpRequest;
import com.teddyguy.meetupapp.dto.response.JwtResponse;
import com.teddyguy.meetupapp.service.AuthService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@AllArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class AuthController {
    private AuthService authService;
    private AuthenticationManager authManager;

    @PostMapping("/sign-in")
    public ResponseEntity<JwtResponse> signIn(@Valid @RequestBody SignInRequest request){
        authenticate(request.email(), request.rawPassword());
        return ResponseEntity.ok(authService.signIn(request.email()));
    }

    @PostMapping("/sign-up")
    public ResponseEntity<Void> signUp(@Valid @RequestBody SignUpRequest request){
        authService.signUp(request);
        return ResponseEntity.ok().build();
    }

    private void authenticate(String username, //En réalité on passe le email ici vu que le nom des utilisateurs ne sont pas forcément unique ...
                              String password) {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"invalid-credentials");
        } catch (DisabledException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,"user-disabled");
        }
    }
}
