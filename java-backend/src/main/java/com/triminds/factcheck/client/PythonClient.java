package com.triminds.factcheck.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class PythonClient {
    @Value("${python.service.url}")
    private String pythonServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String callPythonService(String text) {
        return restTemplate.postForObject(pythonServiceUrl, text, String.class);
    }
}