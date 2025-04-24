package com.example.mtbs.exception;

import com.example.mtbs.exception.handler.CustomFieldError;

import java.util.List;

public class FieldErrorException extends RuntimeException {
    private final List<CustomFieldError> errors;

    public FieldErrorException(List<CustomFieldError> errors) {
        super("Validation failed");
        this.errors = errors;
    }

    public List<CustomFieldError> getErrors() {
        return errors;
    }
}
