package com.inatel.gamemanager.exceptions.settings;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
public class GlobalExceptionHandlerTest {

    @Test
    @DisplayName("Test details access methods")
    public void testSetterAndGetterForStatus() {
        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(404);

        assertEquals(404, details.getStatus());
    }

    @Test
    @DisplayName("Test message access methods")
    public void testSetterAndGetterForMessage() {
        ExceptionDetails details = new ExceptionDetails();
        details.setMessage("Not Found");

        assertEquals("Not Found", details.getMessage());
    }

    @Test
    @DisplayName("Test exception stringify")
    public void testToString() {
        ExceptionDetails details = new ExceptionDetails();
        details.setStatus(500);
        details.setMessage("Internal Server Error");

        String expectedString = "ExceptionDetails(status=500, message=Internal Server Error)";
        assertEquals(expectedString, details.toString());
    }
}
