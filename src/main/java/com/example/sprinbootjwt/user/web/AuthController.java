package com.example.sprinbootjwt.user.web;

import com.example.sprinbootjwt.infra.security.jwt.dto.TokenResponse;
import com.example.sprinbootjwt.user.service.AuthService;
import com.example.sprinbootjwt.user.web.dto.req.LoginRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody final LoginRequest loginRequest) {
        TokenResponse accessToken = authService.createAccessToken(loginRequest);
        return ResponseEntity.ok(accessToken);
    }

}
