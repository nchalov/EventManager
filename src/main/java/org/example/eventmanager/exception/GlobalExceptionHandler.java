package org.example.eventmanager.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            ConstraintViolationException.class,
            MethodArgumentNotValidException.class
    })
    protected ResponseEntity<ErrorMessageResponse> handleConstraintViolation(Exception e) {
        var error = new ErrorMessageResponse("Невалидный аргумент", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessageResponse> handleGenericException(Exception e) {
        var error = new ErrorMessageResponse("Внутренняя ошибка сервера", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({
            IllegalArgumentException.class,
            NullPointerException.class
    })
    protected ResponseEntity<ErrorMessageResponse> handleNotExistException(Exception e) {
        var error = new ErrorMessageResponse("Невалидный запрос", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
