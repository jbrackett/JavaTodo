package com.brackett.sample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UnknownTodoException extends RuntimeException{

    public UnknownTodoException(String message) {
        super(message);
    }

    public UnknownTodoException(String message, Exception e) {
        super(message, e);
    }
}
