package com.ivan.nutriapp.infrastructure.spring;

import com.ivan.nutriapp.application.RecipeUseCase;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAClient;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAClientAdapter;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAFoodRepository;
import com.ivan.nutriapp.infrastructure.repositories.recipe.H2RecipeRepository;
import com.ivan.nutriapp.infrastructure.resources.foodper100grams.FoodResourceAdapter;
import com.ivan.nutriapp.infrastructure.resources.recipe.RecipeResourceAdapter;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.support.RetryTemplate;

@Configuration @EnableRetry
public class SpringConfiguration {

    @Bean
    public USDAClientAdapter foodAdapter() {
        return new USDAClientAdapter();
    }

    @Bean
    public FoodResourceAdapter foodResourceAdapter() {
        return new FoodResourceAdapter();
    }

    @Bean
    public RecipeResourceAdapter recipeResourceAdapter() {
        return new RecipeResourceAdapter();
    }

    @Bean
    public USDAClient usdaClient(USDAFoodConfiguration configuration, RestTemplateWithRetry restTemplate) {
        return new USDAClient(restTemplate, configuration);
    }

    @Bean
    public USDAFoodRepository usdaFoodRepository(
        USDAClient usdaClient,
        USDAClientAdapter adapter) {

        return new USDAFoodRepository(usdaClient, adapter);
    }

    @Bean
    public H2RecipeRepository h2RecipeRepository() {
        return new H2RecipeRepository();
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

    @Bean
    public RecipeUseCase recipeUseCase(USDAFoodRepository foodRepository, H2RecipeRepository h2RecipeRepository) {
        return new RecipeUseCase(foodRepository, h2RecipeRepository);
    }
}
