package com.triminds.factcheck.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeaviateConfig {

    @Bean
    public String weaviateEndpoint() {
        // 🔑 Ajuste para sua instância Weaviate
        // return "https://your-weaviate-instance";
        return "";
    }
}
