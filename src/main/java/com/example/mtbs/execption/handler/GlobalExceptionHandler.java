package com.example.mtbs.execption.handler;
import com.example.mtbs.utility.ResponseFactory;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

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
}
