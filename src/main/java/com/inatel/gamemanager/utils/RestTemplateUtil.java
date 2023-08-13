package com.inatel.gamemanager.utils;

import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateUtil {

    private final RestTemplate restTemplate;

    public RestTemplateUtil() {
        this.restTemplate = new RestTemplate();
    }

    public ResponseEntity<String> get(String url) {
        return restTemplate.exchange(url, HttpMethod.GET, null, String.class);
    }

    public ResponseEntity<String> post(String url, Object requestBody, HttpHeaders headers) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    }
}
