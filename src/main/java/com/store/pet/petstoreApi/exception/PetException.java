package com.store.pet.petstoreApi.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
public class PetException extends RuntimeException {
    private int errorStatusCode;
    private String errorReason;

    public PetException(int errorStatusCode, String errorReason, Exception e){
        this.errorStatusCode = errorStatusCode;
        this.errorReason =errorReason;
        this.addSuppressed(e);
    }
    public PetException(int errorStatusCode, String errorReason){
        this.errorStatusCode = errorStatusCode;
        this.errorReason =errorReason;
    }
    public PetException(){

    }
}
