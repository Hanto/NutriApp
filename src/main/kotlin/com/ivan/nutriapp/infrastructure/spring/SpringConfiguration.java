package com.ivan.nutriapp.infrastructure.spring;

import com.ivan.nutriapp.infrastructure.repositories.USDAFoodRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringConfiguration {

    @Bean
    public USDAFoodRepository usdaFoodRepository(USDAFoodConfiguration configuration, RestTemplate restTemplate) {
        return new USDAFoodRepository(restTemplate, configuration);
    }

    @Bean
    public RestTemplate usdaFoodRestTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}
