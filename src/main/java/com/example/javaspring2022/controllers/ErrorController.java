package com.example.javaspring2022.controllers;

import com.example.javaspring2022.models.dto.ErrorDTO;
import jakarta.validation.ConstraintViolationException;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ErrorDTO ErrorValidation(ConstraintViolationException e){

        return new ErrorDTO(500,e.getMessage());

    }
}
