package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.infrastructure.spring.USDAClientConfiguration;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

@AllArgsConstructor
public class USDAClient {

    private final RestOperations restTemplate;
    private final USDAClientConfiguration configuration;
    private final Logger log = LoggerFactory.getLogger(USDAClient.class);


    public FoodByIdEntity findBy(FoodId id) {

        var url = configuration.getBaseUrl() + configuration.getGetFoodByIdEndpoint() + id.getValue() + "?api_key=" + configuration.getApiKey();
        var payload = new HttpEntity<>(null, baseHeaders());

        log.info("calling the USDA: {}", url);

        return restTemplate.exchange(url, HttpMethod.GET, payload, FoodByIdEntity.class).getBody();
    }

    public FoodSearchResponse findByName(USDAFindByNameRequest request) {

        var url = configuration.getBaseUrl() + configuration.getSearchFoodEndpoint() + "?api_key=" + configuration.getApiKey();

        var payload = new HttpEntity<>(request, baseHeaders());

        log.info("calling the USDA: {}", url);
        log.info("calling the USDA: {}", request);

        return restTemplate.exchange(url, HttpMethod.POST, payload, FoodSearchResponse.class).getBody();
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
