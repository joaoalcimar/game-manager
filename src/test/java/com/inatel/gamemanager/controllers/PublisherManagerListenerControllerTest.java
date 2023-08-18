package com.inatel.gamemanager.controllers;

import com.inatel.gamemanager.clients.publishermanager.PublisherManagerClient;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import com.inatel.gamemanager.models.dtos.responses.HttpResponse;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;

@RunWith(SpringRunner.class)
public class PublisherManagerListenerControllerTest {

    @Mock
    private PublisherManagerClient publisherManagerClient;

    @InjectMocks
    private PublishManagerListenerController controller;

    @Test
    @DisplayName("Test cache clean up endpoint succeeded")
    public void testCacheCleanUpSuccess() {
        doNothing().when(publisherManagerClient).clearPublishersAllowListCache();

        HttpResponse response = controller.cacheCleanUp();

        assertEquals("The cache has been cleared successfully.", response.getMessage());
        assertEquals(HttpStatus.OK, response.getHttpStatus());
    }

    @Test
    @DisplayName("Test cache clean up endpoint failed")
    public void testCacheCleanUpException() {
        doThrow(new RuntimeException()).when(publisherManagerClient).clearPublishersAllowListCache();

        assertThrows(UnexpectedResponseException.class, () -> controller.cacheCleanUp());
    }
}
