package com.inatel.gamemanager.clients.publishermanager;

import com.inatel.gamemanager.clients.publishermanager.models.RegisterRequestBody;
import com.inatel.gamemanager.configs.PublishManagerServiceConfig;
import com.inatel.gamemanager.exceptions.UnexpectedResponseException;
import com.inatel.gamemanager.utils.JsonConverterUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

import static com.inatel.gamemanager.constants.GameManagerApiUrls.PORT;
import static com.inatel.gamemanager.constants.GameManagerApiUrls.BASE_URL;
import static com.inatel.gamemanager.constants.PublishManagerApiUrls.*;

@Slf4j
@Component
@Data
public class PublisherManagerClient implements ApplicationListener<ContextRefreshedEvent> {

    private final WebClient webClient;

    private PublishManagerServiceConfig publishManagerServiceConfig;

    public PublisherManagerClient(WebClient.Builder webClientBuilder,
                                  PublishManagerServiceConfig publishManagerServiceConfig) {
        this.publishManagerServiceConfig = publishManagerServiceConfig;
        this.webClient = webClientBuilder.baseUrl(publishManagerServiceConfig.getPublisherManagerBaseUrl()).build();
    }

    @Cacheable("publishersAllowList")
    public Map<String, String> getPublishersAllowList() {

        try{
            String publisherClientResponseRaw =
                    webClient.get().uri(PUBLISHER_ENDPOINT)
                            .retrieve()
                            .bodyToMono(String.class)
                            .block();

            Map<String, String> publishersAllowList =
                    JsonConverterUtil.convertStringToMapFromClientResponse(publisherClientResponseRaw);

            return publishersAllowList;

        } catch (Exception e){
            log.error("Communication with Publisher API goes wrong.");
            log.error(e.getMessage());
            throw new UnexpectedResponseException("We apologize, but an unexpected error occurred on our server. " +
                    "Contact Support.");
        }
    }

    @CacheEvict(value = "publishersAllowList", allEntries = true)
    public void clearPublishersAllowListCache() {
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        try{

            RegisterRequestBody requestBody = new RegisterRequestBody(BASE_URL, PORT);

            webClient.post().uri(NOTIFICATION_ENDPOINT)
                    .body(BodyInserters.fromValue(requestBody))
                    .retrieve()
                    .toBodilessEntity()
                    .block();
        }catch (Exception e){
            log.error("Failed to register in Publisher Manager Api.");
            log.error(e.getMessage());
        }

        log.info("Game Manager API successfully registered in Publisher Manager.");
    }
}
