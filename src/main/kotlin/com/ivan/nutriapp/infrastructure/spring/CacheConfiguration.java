package com.ivan.nutriapp.infrastructure.spring;

import com.hazelcast.config.Config;
import com.hazelcast.config.YamlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.net.URL;

@Configuration @EnableCaching
public class CacheConfiguration {

    public HazelcastInstance hazelcastInstance(ResourcePatternResolver resourceResolver) throws IOException {

        URL configURL = resourceResolver.getResource("classpath:hazelcast.yml").getURL();
        Config config = new YamlConfigBuilder(configURL).build();

        return Hazelcast.newHazelcastInstance(config);

    }
}
