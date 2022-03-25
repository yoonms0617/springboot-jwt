package com.example.sprinbootjwt.common.error;

import lombok.Getter;

@Getter
public enum ErrorType {

    INVALID_VALUE("ERR-C000", "요청 값이 잘못되었습니다."),

    NOT_FOUND_USER("ERR-U000", "사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL("ERR-U001", "사용 중인 이메일입니다."),
    LOGIN_FAIL("ERR-003", "이메일 또는 비밀번호가 잘못 입력되었습니다.");

    private final String code;
    private final String message;

    ErrorType(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
