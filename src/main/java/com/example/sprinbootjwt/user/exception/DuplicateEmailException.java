package com.example.sprinbootjwt.user.exception;

import com.example.sprinbootjwt.common.error.ErrorType;
import com.example.sprinbootjwt.common.error.exception.BaseException;
import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends BaseException {

    public DuplicateEmailException() {
        super(ErrorType.DUPLICATE_EMAIL, HttpStatus.BAD_REQUEST);
    }

}
