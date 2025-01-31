package com.ivan.nutriapp.infrastructure.spring;

import com.ivan.nutriapp.application.RecipeUseCase;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.FoodEntityAdapter;
import com.ivan.nutriapp.infrastructure.repositories.foodper100grams.USDAFoodRepository;
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
    public FoodEntityAdapter foodAdapter() {
        return new FoodEntityAdapter();
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

    @Bean
    public RecipeUseCase recipeUseCase(USDAFoodRepository foodRepository) {
        return new RecipeUseCase(foodRepository);
    }
}
