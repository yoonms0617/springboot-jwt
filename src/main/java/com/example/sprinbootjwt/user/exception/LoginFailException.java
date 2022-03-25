package com.example.sprinbootjwt.user.exception;

import com.example.sprinbootjwt.common.error.ErrorType;
import com.example.sprinbootjwt.common.error.exception.BaseException;
import org.springframework.http.HttpStatus;

public class LoginFailException extends BaseException {

    public LoginFailException() {
        super(ErrorType.LOGIN_FAIL, HttpStatus.BAD_REQUEST);
    }

}
