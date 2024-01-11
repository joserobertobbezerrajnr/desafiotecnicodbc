package com.sicred.poc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PocAssembleiaException extends Exception {

    public PocAssembleiaException(String message, Exception exception) {
        super(message, exception);
    }

    public PocAssembleiaException(PocSicredErrors message) {
        super(message.getMessage());
    }
}
