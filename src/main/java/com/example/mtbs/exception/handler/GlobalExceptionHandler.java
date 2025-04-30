package com.example.mtbs.exception.handler;

import com.example.mtbs.exception.FieldErrorException;
import com.example.mtbs.exception.UserAlreadyExistsException;
import com.example.mtbs.exception.UserNotInsertedException;
import com.example.mtbs.utility.ErrorResponse;
import com.example.mtbs.utility.ErrorResponse.FieldErrorDetail;
import com.example.mtbs.utility.ResponseFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@AllArgsConstructor
public class GlobalExceptionHandler {

    private final ResponseFactory responseFactory;

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExists(UserAlreadyExistsException ex) {
        return responseFactory.errorResponse("Registration Failed", ex.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotInsertedException.class)
    public ResponseEntity<?> handleUserInsertError(UserNotInsertedException ex) {
        return responseFactory.errorResponse("Registration Failed", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralError(Exception ex) {
        return responseFactory.errorResponse("Internal Error", ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<FieldErrorDetail> fieldErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new FieldErrorDetail(error.getField(), error.getDefaultMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .error("Bad Request")
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(FieldErrorException.class)
    public ResponseEntity<?> handleFieldErrorException(FieldErrorException ex) {
        List<FieldErrorDetail> fieldErrors = ex.getErrors()
                .stream()
                .map(error -> new FieldErrorDetail(error.getField(), error.getMessage()))
                .collect(Collectors.toList());

        ErrorResponse errorResponse = ErrorResponse.builder()
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .error("Bad Request")
                .fieldErrors(fieldErrors)
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
