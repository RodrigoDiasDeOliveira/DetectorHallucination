package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrchestrationService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final String pythonServiceUrl = "http://localhost:8000/verify";

    public ResponseEvaluation verifyText(String text) {
        return restTemplate.postForObject(pythonServiceUrl, text, ResponseEvaluation.class);
    }
}
