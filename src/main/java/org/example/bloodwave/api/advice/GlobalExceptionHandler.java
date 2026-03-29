package org.example.bloodwave.api.advice;

import org.example.bloodwave.application.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<Object> buildErrorResponse(Exception ex, HttpStatus status) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", ex.getMessage());
        return new ResponseEntity<>(body, status);
    }

    @ExceptionHandler({
            CollecteNotFoundException.class,
            DemandeSangNotFoundException.class,
            DonNotFoundException.class,
            DonneurNotFoundException.class,
            HopitalNotFoundException.class,
            InscriptionCollecteNotFoundException.class,
            PasswordResetTokenNotFoundException.class,
            StockSangNotFoundException.class,
            UnitSangNotFoundException.class,
            UserNotFoundException.class
    })
    public ResponseEntity<Object> handleNotFoundExceptions(RuntimeException ex) {
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({
            CollecteAlreadyScheduledException.class,
            EmailAlreadyExistsException.class
    })
    public ResponseEntity<Object> handleConflictExceptions(RuntimeException ex) {
        return buildErrorResponse(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler({
            PasswordNotMatchException.class,
            PasswordResetExpiredException.class
    })
    public ResponseEntity<Object> handleBadRequestExceptions(RuntimeException ex) {
        return buildErrorResponse(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex) {
        return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
