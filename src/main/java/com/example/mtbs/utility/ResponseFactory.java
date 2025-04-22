package com.example.mtbs.utility;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseFactory {

    // Success response
    public <T> ResponseEntity<ResponseStructure<T>> successResponse(T data, String message, HttpStatus status) {
        ResponseStructure<T> response = ResponseStructure.<T>builder()
                .status(status.value())
                .message(message)
                .data(data)
                .build();
        return new ResponseEntity<>(response, status);
    }

    // Error response
    public ResponseEntity<ErrorResponse> errorResponse(String message, String error, HttpStatus status) {
        ErrorResponse response = ErrorResponse.builder()
                .statusCode(status.value())
                .message(message)
                .error(error)
                .build();
        return new ResponseEntity<>(response, status);
    }
}
