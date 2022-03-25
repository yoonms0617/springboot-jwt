package com.example.sprinbootjwt.infra.security.jwt.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString(of = {"value", "expireTime"})
public class TokenResponse {

    private String value;
    private long expireTime;

    public TokenResponse(String value, long expireTime) {
        this.value = value;
        this.expireTime = expireTime;
    }

}
