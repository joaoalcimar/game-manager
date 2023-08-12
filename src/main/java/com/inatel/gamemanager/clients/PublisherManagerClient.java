package com.inatel.gamemanager.clients;

import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import com.inatel.gamemanager.utils.JsonConverterUtil;
import com.inatel.gamemanager.utils.RestTemplateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.inatel.gamemanager.constants.PublishManagerApiUrls.PUBLISHER_ENDPOINT;

@Slf4j
@Component
public class PublisherManagerClient {

    @Autowired
    private RestTemplateUtil restTemplate;

    public boolean isPublisherValid(String publisherId) {

        try{
            String publisherClientResponseRaw = restTemplate.get(PUBLISHER_ENDPOINT).getBody();

            Map<String, String> publishersAllowList =
                    JsonConverterUtil.convertStringToMapFromClientResponse(publisherClientResponseRaw);

            return publishersAllowList.containsKey(publisherId);

        } catch (Exception e){
            log.error("Communication with Publisher API goes wrong");
            e.printStackTrace();
            throw new UnexpectedResponseException("We apologize, but an unexpected error occurred on our server. " +
                    "Contact Support.");
        }

    }

}
