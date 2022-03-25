package com.example.sprinbootjwt.infra.security.service;

import com.example.sprinbootjwt.common.error.ErrorType;
import com.example.sprinbootjwt.user.entity.User;
import com.example.sprinbootjwt.user.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("email = " + email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ErrorType.NOT_FOUND_USER.getMessage()));
        UserDetails build = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().getKey())
                .build();
        System.out.println("build.getAuthorities() = " + build.getAuthorities());
        return build;
    }

}
