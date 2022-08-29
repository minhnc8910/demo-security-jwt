package com.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private Map<String, Object> body = new LinkedHashMap<>();

    //bắt xử lý ngoại lệ
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<?> numberFormatException(NumberFormatException e){
        body.put("Timestamp", new Date());
        body.put("Status", HttpStatus.BAD_REQUEST);
        body.put("Errors", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<?> illegalStateException(NumberFormatException e){
        body.put("Timestamp", new Date());
        body.put("Status", HttpStatus.BAD_REQUEST);
        body.put("Errors", e.getMessage());
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        body.put("Timestamp", new Date());
        body.put("Status", status.value());

        //get all error
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getDefaultMessage())
                .collect(Collectors.toList());
        body.put("Errors", errors);
        return new ResponseEntity<>(body, headers, status);
    }
}
