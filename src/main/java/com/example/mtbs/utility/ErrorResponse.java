package com.example.mtbs.utility;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private int statusCode;
    private String message;
    private String error;



}
