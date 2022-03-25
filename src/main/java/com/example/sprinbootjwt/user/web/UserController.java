package com.example.sprinbootjwt.user.web;

import com.example.sprinbootjwt.user.service.UserService;
import com.example.sprinbootjwt.user.web.dto.req.SignupRequest;
import com.example.sprinbootjwt.user.web.dto.res.ProfileResponse;
import com.example.sprinbootjwt.util.LoginUser;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Void> signup(@Valid @RequestBody final SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.created(URI.create("/")).build();
    }

    @GetMapping("/profile")
    public ResponseEntity<ProfileResponse> profile(@LoginUser final String email) {
        ProfileResponse profile = userService.profile(email);
        return ResponseEntity.ok(profile);
    }

}
