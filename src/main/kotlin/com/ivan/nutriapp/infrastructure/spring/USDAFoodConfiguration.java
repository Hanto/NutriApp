package com.ivan.nutriapp.infrastructure.spring;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration @ToString @Getter
public class USDAFoodConfiguration {

    @Value("${externalapi.usdafood.baseurl}")
    private String baseUrl;
    @Value("${externalapi.usdafood.endpoints.getfoodbyid}")
    private String getFoodByIdEndpoint;
    @Value("${externalapi.usdafood.apikey}")
    private String apiKey;
}
