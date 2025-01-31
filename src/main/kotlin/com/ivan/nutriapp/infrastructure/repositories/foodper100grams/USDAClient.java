package com.ivan.nutriapp.infrastructure.repositories.foodper100grams;

import com.ivan.nutriapp.domain.nutrition.FoodId;
import com.ivan.nutriapp.infrastructure.spring.USDAFoodConfiguration;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestOperations;

import java.util.List;

@AllArgsConstructor
public class USDAClient {

    private final RestOperations restTemplate;
    private final USDAFoodConfiguration configuration;
    private final Logger log = LoggerFactory.getLogger(USDAClient.class);


    public FoodEntity findBy(FoodId id) {

        var url = configuration.getBaseUrl() + configuration.getGetFoodByIdEndpoint() + id.getValue() + "?api_key=" + configuration.getApiKey();
        var payload = new HttpEntity(null, baseHeaders());

        log.info("calling the USDA: {}", url);

        return restTemplate.exchange(url, HttpMethod.GET, payload, FoodEntity.class).getBody();
    }

    public String findByName(USDAFindByNameRequest request) {

        var url = configuration.getBaseUrl() + "/v1/foods/search/" + "?api_key=" + configuration.getApiKey();
        var payload = new HttpEntity(request, baseHeaders());

        log.info("calling the USDA: {}", url);

        return restTemplate.exchange(url, HttpMethod.POST, payload, String.class).getBody();
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
