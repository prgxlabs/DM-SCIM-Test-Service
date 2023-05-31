package com.prgx.deductionManagement.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.prgx.deductionManagement.model.SCIMError;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { ConstraintViolationException.class })
    @ResponseStatus(HttpStatus.CONFLICT)
    protected ResponseEntity<SCIMError> handleConflict(RuntimeException ex, WebRequest request){
        SCIMError error = SCIMError.builder()
                .code(100L)
                .message("UserName already exists")
                .build();
//        return handleExceptionInternal(ex, error,
//                new HttpHeaders(), HttpStatus.CONFLICT, request);
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    /*@ExceptionHandler(value = { MethodArgumentNotValidException.class })
    protected ResponseEntity<Object> handleInputDataValidation(
            MethodArgumentNotValidException ex,
            WebRequest request) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        ObjectMapper objectMapper = new ObjectMapper();
        SCIMError error = SCIMError.builder().code(101L)
                .message(objectMapper.writeValueAsString(errors)).build();
        return handleExceptionInternal(ex, error,
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }*/

    @ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<SCIMError> handleIOException(RuntimeException ex, WebRequest request){
        SCIMError error = SCIMError.builder()
                .code(500L)
                .message("Unknown server error")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
