package com.example.sprinbootjwt.user.service;

import com.example.sprinbootjwt.user.entity.User;
import com.example.sprinbootjwt.user.exception.DuplicateEmailException;
import com.example.sprinbootjwt.user.exception.NotFoundUserException;
import com.example.sprinbootjwt.user.repository.UserRepository;
import com.example.sprinbootjwt.user.web.dto.req.SignupRequest;
import com.example.sprinbootjwt.user.web.dto.res.ProfileResponse;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void signup(SignupRequest signupRequest) {
        checkEmailDuplicate(signupRequest.getEmail());
        String encoded = encryptionPassword(signupRequest.getPassword());
        User user = User.builder()
                .name(signupRequest.getName())
                .email(signupRequest.getEmail())
                .password(encoded)
                .build();
        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public ProfileResponse profile(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(NotFoundUserException::new);
        return new ProfileResponse(user);
    }

    private void checkEmailDuplicate(String email) {
        if (isExistsEmail(email)) {
            throw new DuplicateEmailException();
        }
    }

    private boolean isExistsEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    private String encryptionPassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

}
