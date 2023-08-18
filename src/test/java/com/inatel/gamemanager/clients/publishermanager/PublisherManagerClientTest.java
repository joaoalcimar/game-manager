package com.inatel.gamemanager.clients.publishermanager;

import com.inatel.gamemanager.configs.PublishManagerServiceConfig;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class PublisherManagerClientTest {

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
        assertThrows(UnexpectedResponseException.class, () -> publisherManagerClient.getPublishersAllowList());
    }

    @Test
    @DisplayName("Test cache clearing")
    public void testClearPublishersAllowListCache(){
        assertDoesNotThrow(() -> publisherManagerClient.clearPublishersAllowListCache());
    }




}
