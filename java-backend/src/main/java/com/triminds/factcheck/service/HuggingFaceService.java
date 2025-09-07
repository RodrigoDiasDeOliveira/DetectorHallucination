package com.triminds.factcheck.service;

import com.triminds.factcheck.config.HuggingFaceConfig;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class HuggingFaceService {

    private final RestTemplate restTemplate;
    private final HuggingFaceConfig config;

    public HuggingFaceService(RestTemplate restTemplate, HuggingFaceConfig config) {
        this.restTemplate = restTemplate;
        this.config = config;
    }

    public String getEmbedding(String text) {
        String model = "sentence-transformers/all-MiniLM-L6-v2"; // modelo leve de embeddings
        String url = config.getApiUrl() + "/pipeline/feature-extraction/" + model;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (config.getApiKey() != null && !config.getApiKey().isEmpty()) {
            headers.set("Authorization", "Bearer " + config.getApiKey());
        }

        Map<String, String> payload = new HashMap<>();
        payload.put("inputs", text);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(payload, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            return response.getBody(); // 🔧 vamos melhorar parsing depois
        } catch (Exception e) {
            throw new RuntimeException("Erro ao chamar Hugging Face API: " + e.getMessage(), e);
        }
    }
}
