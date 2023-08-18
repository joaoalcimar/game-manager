package com.inatel.gamemanager.models.dtos.responses;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class HttpResponseTest {

    @Test
    @DisplayName("Test default object creation")
    public void testHttpResponseConstructorAndGetters() {
        String message = "Success";
        HttpStatusCode httpStatus = HttpStatusCode.valueOf(200);

        HttpResponse response = new HttpResponse(message, httpStatus);

        assertNotNull(response);
        assertEquals(message, response.getMessage());
        assertEquals(httpStatus, response.getHttpStatus());
    }
}
