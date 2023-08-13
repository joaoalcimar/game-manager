package com.inatel.gamemanager.controllers;

import com.inatel.gamemanager.clients.publishermanager.PublisherManagerClient;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import com.inatel.gamemanager.models.dtos.responses.HttpResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("publishercache")
public class PublishManagerListenerController {

    @Autowired
    private PublisherManagerClient publisherManagerClient;

    @DeleteMapping
    public HttpResponse cacheCleanUp() {
        try{
            publisherManagerClient.clearPublishersAllowListCache();
        }catch (Exception e){
            log.error("Something went wrong in publishers allow list cache clean up.");
            e.printStackTrace();
            throw new UnexpectedResponseException("Internal Server Error. Contact Support.");
        }

        log.info("Publishers allow list cache clean up succeeded.");

        return new HttpResponse("The cache has been cleared successfully.", HttpStatus.OK);
    }
}
