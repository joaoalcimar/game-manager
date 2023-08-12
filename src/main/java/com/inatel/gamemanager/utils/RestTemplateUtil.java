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

    public ResponseEntity<String> put(String url, Object requestBody, HttpHeaders headers) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(requestBody, headers);
        return restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
    }

    public ResponseEntity<String> delete(String url, HttpHeaders headers) {
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
    }
}
