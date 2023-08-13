package com.inatel.gamemanager.clients.publishermanager;

import com.inatel.gamemanager.clients.publishermanager.models.RegisterRequestBody;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import com.inatel.gamemanager.utils.JsonConverterUtil;
import com.inatel.gamemanager.utils.RestTemplateUtil;
import jakarta.annotation.PostConstruct;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.inatel.gamemanager.constants.GameManagerApiUrls.PORT;
import static com.inatel.gamemanager.constants.GameManagerApiUrls.BASE_URL;
import static com.inatel.gamemanager.constants.PublishManagerApiUrls.*;

@Slf4j
@Component
@Data
public class PublisherManagerClient {

    @Autowired
    private RestTemplateUtil restTemplate;

    @Cacheable("publishersAllowList")
    public Map<String, String> getPublishersAllowList() {

        try{
            String publisherClientResponseRaw = restTemplate.get(PUBLISHER_ENDPOINT).getBody();

            Map<String, String> publishersAllowList =
                    JsonConverterUtil.convertStringToMapFromClientResponse(publisherClientResponseRaw);

            return publishersAllowList;

        } catch (Exception e){
            log.error("Communication with Publisher API goes wrong.");
            e.printStackTrace();
            throw new UnexpectedResponseException("We apologize, but an unexpected error occurred on our server. " +
                    "Contact Support.");
        }

    }

    @CacheEvict(value = "publishersAllowList", allEntries = true)
    public void clearPublishersAllowListCache() {
    }

    @PostConstruct
    public void register(){
        try{
            RegisterRequestBody requestBody = new RegisterRequestBody(BASE_URL, PORT);

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.setContentType(MediaType.APPLICATION_JSON);

            restTemplate.post(NOTIFICATION_ENDPOINT, requestBody, httpHeaders);
        }catch (Exception e){
            log.error("Failed to register in Publisher Manager Api.");
            e.printStackTrace();
        }

        log.info("Game Manager API successfully registered in Publisher Manager.");
    }

}
