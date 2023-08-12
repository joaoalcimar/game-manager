package com.inatel.gamemanager.exceptions.settings;

import com.inatel.gamemanager.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EmptyStringException.class)
    public ResponseEntity<?> handleEmptyStringException(EmptyStringException emptyStringException){

        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setMessage(emptyStringException.getMessage());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidPublisherException.class)
    public ResponseEntity<?> handleInvalidPublisherException(InvalidPublisherException invalidPublisherException){

        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setMessage(invalidPublisherException.getMessage());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException notFoundException){

        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(HttpStatus.NOT_FOUND.value());
        details.setMessage(notFoundException.getMessage());

        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TimeFormatException.class)
    public ResponseEntity<?> handleAuthenticationException(TimeFormatException timeFormatException){

        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setMessage(timeFormatException.getMessage());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException validationException){

        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(HttpStatus.BAD_REQUEST.value());
        details.setMessage(validationException.getMessage());

        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}
