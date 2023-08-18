package com.inatel.gamemanager.clients.publishermanager;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;

import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
public class PublisherManagerClientTest {

    @Before
    public void setUp() {
//        publisherManagerClient = new PublisherManagerClient();
//        publisherManagerClient.setRestTemplate(restTemplate);
//        publisherManagerClient.setPublishManagerService(publishManagerService);
    }

    @Test
    @DisplayName("Test allow list returning from publisher api")
    public void testGetPublishersAllowListSuccess(){

    }

    @Test
    @DisplayName("Test allow list getting throwing exception")
    public void testGetPublishersAllowListException(){
    }

    @Test
    @DisplayName("Test cache clearing")
    public void testClearPublishersAllowListCache(){
    }




}
