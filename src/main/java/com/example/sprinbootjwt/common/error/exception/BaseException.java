package com.example.sprinbootjwt.common.error.exception;

import com.example.sprinbootjwt.common.error.ErrorType;

import lombok.Getter;

import org.springframework.http.HttpStatus;

@Getter
public class BaseException extends RuntimeException {

    private final ErrorType errorType;
    private final HttpStatus httpStatus;

    public BaseException(ErrorType errorType, HttpStatus httpStatus) {
        super(errorType.getMessage());
        this.errorType = errorType;
        this.httpStatus = httpStatus;
    }

}
