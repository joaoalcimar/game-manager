package com.inatel.gamemanager.utils;

import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RestTemplateUtilTest {

    @Test
    @DisplayName("Test Get with correct mocked parameters")
    public void testGetRequest() {
        RestTemplate restTemplateMock = mock(RestTemplate.class);
        RestTemplateUtil restTemplateUtil = new RestTemplateUtil(restTemplateMock);

        ResponseEntity<String> expectedResponse = new ResponseEntity<>("Response body", HttpStatus.OK);
        when(restTemplateMock.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(String.class)))
                .thenReturn(expectedResponse);

        ResponseEntity<String> response = restTemplateUtil.get("http://example.com");

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Response body", response.getBody());
    }

}
