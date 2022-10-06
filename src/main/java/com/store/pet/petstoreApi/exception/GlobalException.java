package com.store.pet.petstoreApi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException extends Exception{
    @ExceptionHandler(PetException.class)
    public ResponseEntity<PetException> handleEmptyInput(PetException petException ){
        ResponseEntity<PetException> petExceptionResponseEntity = new ResponseEntity<PetException>(petException,HttpStatus.BAD_REQUEST);
        return petExceptionResponseEntity;
    }
}
