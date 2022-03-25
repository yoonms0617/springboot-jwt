package com.example.sprinbootjwt.user.web.dto.res;

import com.example.sprinbootjwt.user.entity.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ProfileResponse {

    private String name;
    private String email;

    public ProfileResponse(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }

}
