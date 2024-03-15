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

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ErrorMessageResponse> handleConstraintViolation(ConstraintViolationException e) {
        var error = new ErrorMessageResponse("Невалидный аргумент", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorMessageResponse> handleArgumentNotValidException(MethodArgumentNotValidException e) {
        var error = new ErrorMessageResponse("Невалидный аргумент", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorMessageResponse> handleGenericException(Exception e) {
        var error = new ErrorMessageResponse("Внутренняя ошибка сервера", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorMessageResponse> handleNotExistException(IllegalArgumentException e) {
        var error = new ErrorMessageResponse("Сущность не найдена", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CapacityException.class)
    protected ResponseEntity<ErrorMessageResponse> handleCapacityException(CapacityException e) {
        var error = new ErrorMessageResponse("Некорректная вместимость локации", e.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
