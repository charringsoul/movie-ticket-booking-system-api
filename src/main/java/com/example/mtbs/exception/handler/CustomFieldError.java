package com.example.mtbs.exception.handler;

public class CustomFieldError {
    private String field;
    private String message;

    public CustomFieldError(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}

