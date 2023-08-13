package com.inatel.gamemanager.configs;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:publisher-manager-service.properties")
public class PublishManagerServiceConfig {

    @Value("${app-config.services.publisher-manager}")
    private String publisherManagerBaseUrl;
}
