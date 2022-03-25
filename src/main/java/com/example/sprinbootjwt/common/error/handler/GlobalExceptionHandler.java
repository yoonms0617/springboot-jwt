package com.example.sprinbootjwt.common.error.handler;

import com.example.sprinbootjwt.common.error.ErrorResponse;
import com.example.sprinbootjwt.common.error.ErrorType;
import com.example.sprinbootjwt.common.error.exception.BaseException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorResponse> handleBaseException(BaseException e) {
        return ResponseEntity.status(e.getHttpStatus()).body(ErrorResponse.of(e.getErrorType()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return ResponseEntity.badRequest().body(ErrorResponse.of(ErrorType.INVALID_VALUE, e.getBindingResult()));
    }

}
