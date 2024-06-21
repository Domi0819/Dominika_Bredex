package com.example.demo.controllers;

import com.example.demo.exceptions.ClientException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.*;

@ControllerAdvice
public class ExceptionHandlingController {

    ExceptionHandlingController(){};

    @ExceptionHandler({ClientException.class})
    public ResponseEntity<Object> handleEmailInvalidException(ClientException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String>errorMap=new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error->
        {
            errorMap.add(error.getField() + ':' + error.getDefaultMessage());
        });
        return new ResponseEntity<>(getErrorsMap(errorMap), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }

}
