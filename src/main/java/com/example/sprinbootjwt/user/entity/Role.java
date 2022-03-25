package com.example.sprinbootjwt.user.entity;

import lombok.Getter;

@Getter
public enum Role {

    USER("USER"), ADMIN("ADMIN");

    private final String key;

    Role(String key) {
        this.key = key;
    }

}
