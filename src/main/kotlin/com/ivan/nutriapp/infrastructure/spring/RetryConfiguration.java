package com.ivan.nutriapp.infrastructure.spring;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration @ToString @Getter
public class RetryConfiguration {

    @Value("${externalapi.retry.maxattemps}")
    private Integer maxAttemps;
    @Value("#{'${externalapi.retry.httpcodes}'.split(',')}")
    private List<Integer> httpCodesToRetry;
    @Value("${externalapi.retry.backoffinterval}")
    private Long backOffInterval;
    @Value("${externalapi.retry.backoffmultiplier}")
    private Long backOffMultiplier;

}
