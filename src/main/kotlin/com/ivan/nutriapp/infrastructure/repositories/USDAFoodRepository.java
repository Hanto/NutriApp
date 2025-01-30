package com.ivan.nutriapp.infrastructure.repositories;

import com.ivan.nutriapp.domain.Food;
import com.ivan.nutriapp.domain.FoodId;
import com.ivan.nutriapp.infrastructure.spring.RestTemplateWithRetry;
import com.ivan.nutriapp.infrastructure.spring.USDAFoodConfiguration;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

@AllArgsConstructor
public class USDAFoodRepository {

    private final RestOperations restTemplate;
    private final USDAFoodConfiguration configuration;
    private final FoodEntityAdapter adapter;
    private final Logger log = LoggerFactory.getLogger(USDAFoodRepository.class);

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Cacheable("Food")
    public Food findById(FoodId id) {

        var url = configuration.getBaseUrl() + configuration.getGetFoodByIdEndpoint() + id.getValue() + "?api_key=" + configuration.getApiKey();
        var payload = new HttpEntity(null, baseHeaders());

        log.info("calling the USDA: {}", url);

        var response = restTemplate.exchange(url, HttpMethod.GET, payload, FoodEntity.class);
        return adapter.toDomain(response.getBody());
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
