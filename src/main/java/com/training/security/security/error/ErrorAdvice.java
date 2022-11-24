package com.training.security.security.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ErrorAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(IllegalArgumentException exp) {
        return ErrorObj.builder()
                       .withErrorDesc(exp.getMessage())
                       .withErrorCode(1025)
                       .build();
    }

    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(IllegalStateException exp) {
        return ErrorObj.builder()
                       .withErrorDesc(exp.getMessage())
                       .withErrorCode(1026)
                       .build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(MethodArgumentNotValidException exp) {
        return ErrorObj.builder()
                       .withErrorDesc("Validation error")
                       .withErrorCode(1026)
                       .withSubErrors(exp.getAllErrors()
                                         .stream()
                                         .map(se -> ErrorObj.builder()
                                                            .withErrorDesc(se.getDefaultMessage())
                                                            .build())
                                         .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorObj handle(ConstraintViolationException exp) {
        return ErrorObj.builder()
                       .withErrorDesc("Validation error")
                       .withErrorCode(1026)
                       .withSubErrors(exp.getConstraintViolations()
                                         .stream()
                                         .map(se -> ErrorObj.builder()
                                                            .withErrorDesc(se.getMessage())
                                                            .build())
                                         .collect(Collectors.toList()))
                       .build();
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorObj> handle(Exception exp) {
        if (exp instanceof NullPointerException){
            ResponseEntity.status(HttpStatus.EXPECTATION_FAILED)
                          .body(ErrorObj.builder()
                                        .withErrorDesc(exp.getMessage())
                                        .withErrorCode(1025)
                                        .build());
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                             .body(ErrorObj.builder()
                                           .withErrorDesc(exp.getMessage())
                                           .withErrorCode(1025)
                                           .build());
    }

}
