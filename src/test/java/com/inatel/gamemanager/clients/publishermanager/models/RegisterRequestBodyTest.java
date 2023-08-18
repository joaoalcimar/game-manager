package com.inatel.gamemanager.clients.publishermanager.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
public class RegisterRequestBodyTest {

    @Test
    @DisplayName("Test default object creation")
    public void testConstructorAndGetters() {
        String host = "example.com";
        Integer port = 8080;

        RegisterRequestBody requestBody = new RegisterRequestBody(host, port);

        assertNotNull(requestBody);
        assertEquals(host, requestBody.getHost());
        assertEquals(port, requestBody.getPort());
    }

}
