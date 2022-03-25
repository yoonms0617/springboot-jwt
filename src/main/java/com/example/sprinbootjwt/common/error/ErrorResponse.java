package com.example.sprinbootjwt.common.error;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ErrorResponse {

    private LocalDateTime timStamp;
    private String code;
    private String message;
    private List<FieldValue> fieldValues;

    private ErrorResponse(String code, String message) {
        this.timStamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.fieldValues = Collections.emptyList();
    }

    private ErrorResponse(String code, String message, List<FieldValue> fieldValues) {
        this.timStamp = LocalDateTime.now();
        this.code = code;
        this.message = message;
        this.fieldValues = fieldValues;
    }

    public static ErrorResponse of(ErrorType errorType) {
        return new ErrorResponse(errorType.getCode(), errorType.getMessage());
    }

    public static ErrorResponse of(ErrorType errorType, BindingResult bindingResult) {
        return new ErrorResponse(errorType.getCode(), errorType.getMessage(), FieldValue.of(bindingResult));
    }

    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @Getter
    public static class FieldValue {

        private String field;
        private String value;
        private String reason;

        private FieldValue(String field, String value, String reason) {
            this.field = field;
            this.value = value;
            this.reason = reason;
        }

        public static List<FieldValue> of(BindingResult bindingResult) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            return fieldErrors.stream()
                    .map(fieldError -> new FieldValue(
                            fieldError.getField(),
                            fieldError.getRejectedValue() == null ? "" : fieldError.getRejectedValue().toString(),
                            fieldError.getDefaultMessage()
                    )).collect(Collectors.toList());
        }

    }

}
