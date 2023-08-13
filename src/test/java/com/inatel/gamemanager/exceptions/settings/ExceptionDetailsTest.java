package com.inatel.gamemanager.exceptions.settings;

import com.inatel.gamemanager.exceptions.*;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class ExceptionDetailsTest {

    @Test
    @DisplayName("Test empty string exception handler")
    public void testHandleEmptyStringException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        EmptyStringException emptyStringException = new EmptyStringException("Empty string");
        ResponseEntity<?> responseEntity = handler.handleEmptyStringException(emptyStringException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Empty string",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test invalid publisher exception handler")
    public void testInvalidPublisherException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        InvalidPublisherException invalidPublisherException = new InvalidPublisherException("Invalid Publisher.");
        ResponseEntity<?> responseEntity = handler.handleInvalidPublisherException(invalidPublisherException);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Invalid Publisher.",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test not found exception handler")
    public void testHandleNotFoundException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        NotFoundException notFoundException = new NotFoundException("Resource not found.");
        ResponseEntity<?> responseEntity = handler.handleNotFoundException(notFoundException);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Resource not found.",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test time format exception handler")
    public void testHandleTimeFormatException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        TimeFormatException timeFormatException = new TimeFormatException("Invalid time format.");
        ResponseEntity<?> responseEntity = handler.handleTimeFormatException(timeFormatException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid time format.",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test unexpected response exception handler")
    public void testHandleUnexpectedResponseException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        UnexpectedResponseException unexpectedResponseException =
                new UnexpectedResponseException("Unexpected response returned.");
        ResponseEntity<?> responseEntity = handler.handleUnexpectedResponseException(unexpectedResponseException);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Unexpected response returned.",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }

    @Test
    @DisplayName("Test validation exception handler")
    public void testHandleValidationException() {
        GlobalExceptionHandler handler = new GlobalExceptionHandler();
        ValidationException validationException = new ValidationException("Validation failed.");
        ResponseEntity<?> responseEntity = handler.handleValidationException(validationException);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Validation failed.",
                ((ExceptionDetails) Objects.requireNonNull(responseEntity.getBody())).getMessage());
    }
}
