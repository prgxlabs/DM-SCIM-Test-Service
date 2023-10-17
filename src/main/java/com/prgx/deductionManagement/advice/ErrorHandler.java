package com.prgx.deductionManagement.advice;


import com.prgx.deductionManagement.model.SCIMError;
import jakarta.persistence.PersistenceException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = { PersistenceException.class })
    protected ResponseEntity<Object> handleConflict(PersistenceException ex){
        System.out.println(ex.getLocalizedMessage());
        SCIMError error = SCIMError.builder()
                .code(100L)
                .message(ex.getLocalizedMessage())
                .build();
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

    /*@ExceptionHandler(value = { IOException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected ResponseEntity<SCIMError> handleIOException(RuntimeException ex, WebRequest request){
        SCIMError error = SCIMError.builder()
                .code(500L)
                .message("Unknown server error")
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
}
