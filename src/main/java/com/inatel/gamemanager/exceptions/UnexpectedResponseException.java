package com.inatel.gamemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class UnexpectedResponseException extends RuntimeException{

    public UnexpectedResponseException(String message){
        super(message);
    }
}
