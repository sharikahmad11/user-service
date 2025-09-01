package com.sharik.user_service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException(Exception ex) {
        Map<String, String> errorMap = new HashMap<>();
        errorMap.put("details", Arrays.stream(ex.getStackTrace()).map(stack -> stack.toString()).collect(Collectors.joining("/n")));
        errorMap.put("Timestamp", String.valueOf(LocalDateTime.now()));
        errorMap.put("message", ex.getMessage());
        return new ResponseEntity<>(errorMap, HttpStatus.NOT_FOUND);
    }
}
