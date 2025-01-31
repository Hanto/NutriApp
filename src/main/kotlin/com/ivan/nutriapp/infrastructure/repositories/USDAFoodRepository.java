package com.ivan.nutriapp.infrastructure.repositories;

import com.ivan.nutriapp.application.FoodRepository;
import com.ivan.nutriapp.domain.recipe.FoodPer100Grams;
import com.ivan.nutriapp.domain.recipe.FoodId;
import com.ivan.nutriapp.infrastructure.spring.USDAFoodConfiguration;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

@AllArgsConstructor
public class USDAFoodRepository implements FoodRepository {

    private final RestOperations restTemplate;
    private final USDAFoodConfiguration configuration;
    private final FoodEntityAdapter adapter;
    private final Logger log = LoggerFactory.getLogger(USDAFoodRepository.class);

    // MAIN:
    //--------------------------------------------------------------------------------------------------------

    @Cacheable("Food")
    @Override public FoodPer100Grams findById(FoodId id) {

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
