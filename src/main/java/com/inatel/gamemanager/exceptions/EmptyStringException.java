package com.inatel.gamemanager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmptyStringException extends RuntimeException{

    public EmptyStringException(String message){
        super(message);
    }

}
