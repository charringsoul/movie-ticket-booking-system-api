package com.example.mtbs.utility;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    private String error;
    private List<FieldErrorDetail> fieldErrors;

    // Nested class to represent individual field error details
    @Data
    @AllArgsConstructor
    public static class FieldErrorDetail {
        private String field;
        private String message;
    }
}
