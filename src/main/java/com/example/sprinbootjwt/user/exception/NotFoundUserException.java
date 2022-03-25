package com.example.sprinbootjwt.user.exception;

import com.example.sprinbootjwt.common.error.ErrorType;
import com.example.sprinbootjwt.common.error.exception.BaseException;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends BaseException {

    public NotFoundUserException() {
        super(ErrorType.NOT_FOUND_USER, HttpStatus.NOT_FOUND);
    }

}
