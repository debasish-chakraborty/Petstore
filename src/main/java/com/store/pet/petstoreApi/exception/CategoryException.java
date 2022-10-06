package com.store.pet.petstoreApi.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;

@Component
@NoArgsConstructor
public class CategoryException extends RuntimeException {
    private int errorStatusCode;
    private String errorReason;

    public CategoryException(int errorStatusCode, String errorReason, Exception e){
        this.errorStatusCode = errorStatusCode;
        this.errorReason =errorReason;
        this.addSuppressed(e);
    }
    public CategoryException(int errorStatusCode, String errorReason){
        this.errorStatusCode = errorStatusCode;
        this.errorReason =errorReason;
    }


}
