package com.example.sprinbootjwt.user.service;

import com.example.sprinbootjwt.infra.security.jwt.JwtTokenProvider;
import com.example.sprinbootjwt.infra.security.jwt.dto.TokenResponse;
import com.example.sprinbootjwt.user.entity.User;
import com.example.sprinbootjwt.user.exception.LoginFailException;
import com.example.sprinbootjwt.user.repository.UserRepository;
import com.example.sprinbootjwt.user.web.dto.req.LoginRequest;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(JwtTokenProvider jwtTokenProvider,
                       UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    public TokenResponse createAccessToken(LoginRequest loginRequest) {
        User user = userRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(LoginFailException::new);
        checkMatchPassword(loginRequest.getPassword(), user.getPassword());
        return jwtTokenProvider.createAccessToken(user);
    }

    private void checkMatchPassword(String rawPassword, String encoded) {
        if (notMatchPassword(rawPassword, encoded)) {
            throw new LoginFailException();
        }
    }

    private boolean notMatchPassword(String rawPassword, String encoded) {
        return !passwordEncoder.matches(rawPassword, encoded);
    }

}
