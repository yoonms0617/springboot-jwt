package com.example.sprinbootjwt.user.web.dto.req;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"name", "email", "password"})
public class SignupRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String email;

    @NotBlank
    private String password;

}
