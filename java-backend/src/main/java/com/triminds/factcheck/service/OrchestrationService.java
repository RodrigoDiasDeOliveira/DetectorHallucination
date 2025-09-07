package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;

public interface OrchestrationService {
    ResponseEvaluation verify(String llmResponse);
}