package com.ivan.nutriapp.infrastructure.repositories;

import com.ivan.nutriapp.domain.FoodId;
import com.ivan.nutriapp.infrastructure.spring.USDAFoodConfiguration;
import lombok.val;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class USDAFoodRepository {

    private final RestTemplate restTemplate;
    private final USDAFoodConfiguration configuration;
    private final Logger log = LoggerFactory.getLogger(USDAFoodRepository.class);

    public USDAFoodRepository(RestTemplate restTemplate, USDAFoodConfiguration configuration) {

        this.restTemplate = restTemplate;
        this.configuration = configuration;
    }

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    public FoodEntity findById(FoodId id) {

        var url = configuration.getBaseUrl() + configuration.getGetFoodByIdEndpoint() + id.getValue() + "?api_key=" + configuration.getApiKey();
        var payload = new HttpEntity(null, baseHeaders());

        log.info("calling the USDA: {}", url);

        var response = restTemplate.exchange(url, HttpMethod.GET, payload, FoodEntity.class);

        return response.getBody();
    }

    // HELPER:
    //--------------------------------------------------------------------------------------------------------

    private HttpHeaders baseHeaders()
    {
        var httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        httpHeaders.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return httpHeaders;
    }
}
