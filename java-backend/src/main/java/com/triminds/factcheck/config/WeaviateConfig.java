package com.triminds.factcheck.config;

import io.weaviate.client.WeaviateClient;
import io.weaviate.client.v1.WeaviateClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeaviateConfig {
    @Value("${weaviate.url}")
    private String weaviateUrl;

    @Bean
    public WeaviateClient weaviateClient() {
        return new WeaviateClientBuilder().withScheme("http").withHost(weaviateUrl).build();
    }
}