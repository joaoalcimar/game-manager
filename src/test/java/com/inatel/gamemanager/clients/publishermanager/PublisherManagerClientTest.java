package com.inatel.gamemanager.clients.publishermanager;

import com.inatel.gamemanager.clients.publishermanager.models.RegisterRequestBody;
import com.inatel.gamemanager.configs.PublishManagerServiceConfig;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PublisherManagerClientTest {

    private final RestTemplateUtil restTemplate = mock(RestTemplateUtil.class);

    private final PublishManagerServiceConfig publishManagerService = mock(PublishManagerServiceConfig.class);

    private PublisherManagerClient publisherManagerClient;

    @Before
    public void setUp() {
//        publisherManagerClient = new PublisherManagerClient();
//        publisherManagerClient.setRestTemplate(restTemplate);
//        publisherManagerClient.setPublishManagerService(publishManagerService);
    }

    @Test
    @DisplayName("Test allow list returning from publisher api")
    public void testGetPublishersAllowListSuccess(){
        String mockResponse = "[{\"id\":\"1\",\"name\":\"konami\"},{\"id\":\"2\",\"name\":\"capcom\"}]";
        when(restTemplate.get(any())).thenReturn(new ResponseEntity<>(mockResponse, HttpStatus.OK));
        when(publishManagerService.getPublisherManagerBaseUrl()).thenReturn("url");

        Map<String, String> expectedMap = new HashMap<>();
        expectedMap.put("1", "konami");
        expectedMap.put("2", "capcom");


        Map<String, String> result = publisherManagerClient.getPublishersAllowList();
        assertEquals(expectedMap, result);
    }

    @Test
    @DisplayName("Test allow list getting throwing exception")
    public void testGetPublishersAllowListException(){
        when(restTemplate.get(anyString())).thenThrow(new RuntimeException("Mocked exception"));

        assertThrows(UnexpectedResponseException.class, () -> publisherManagerClient.getPublishersAllowList());
    }

    @Test
    @DisplayName("Test cache clearing")
    public void testClearPublishersAllowListCache(){
        assertDoesNotThrow(() -> publisherManagerClient.clearPublishersAllowListCache());
    }

    @Test
    @DisplayName("Test register in publisher api successfully")
    public void testRegisterSuccess(){
        RegisterRequestBody requestBody = new RegisterRequestBody("baseUrl", 1234);
        when(restTemplate.post(anyString(), eq(requestBody), any())).thenReturn(new ResponseEntity<>("", HttpStatus.OK));

        assertDoesNotThrow(() -> publisherManagerClient.register());
    }

    @Test
    @DisplayName("Test register in publisher api failed")
    public void testRegisterException(){
        doThrow(new RuntimeException("Mocked exception")).when(restTemplate).post(anyString(), any(), any());

        assertDoesNotThrow(() -> publisherManagerClient.register());
    }


}
