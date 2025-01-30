package com.ivan.nutriapp.infrastructure.spring;

import com.ivan.nutriapp.infrastructure.repositories.FoodEntityAdapter;
import com.ivan.nutriapp.infrastructure.repositories.USDAFoodRepository;
import com.ivan.nutriapp.infrastructure.resources.FoodResourceAdapter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.policy.ExceptionClassifierRetryPolicy;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

@Configuration @EnableRetry
public class SpringConfiguration {

    @Bean
    public FoodEntityAdapter foodAdapter() {
        return new FoodEntityAdapter();
    }

    @Bean
    public FoodResourceAdapter foodResourceAdapter() {
        return new FoodResourceAdapter();
    }

    @Bean
    public USDAFoodRepository usdaFoodRepository(
        USDAFoodConfiguration configuration,
        RestTemplateWithRetry usdaFoodRestTemplate,
        FoodEntityAdapter adapter) {

        return new USDAFoodRepository(usdaFoodRestTemplate, configuration, adapter);
    }

    @Bean
    public RestTemplateWithRetry usdaFoodRestTemplate(RestTemplateBuilder builder, RetryTemplate defaultRetryTemplateForRest) {

        return new RestTemplateWithRetry(
            builder.build(),
            defaultRetryTemplateForRest
        );
    }

    @Bean
    public RetryTemplate defaultRetyTemplateForRest(RetryConfiguration retryConfiguration) {

        return new RetryTemplateBuilder(
            retryConfiguration.getMaxAttemps(),
            retryConfiguration.getHttpCodesToRetry(),
            retryConfiguration.getBackOffInterval(),
            retryConfiguration.getBackOffMultiplier()).build();
    }
}
