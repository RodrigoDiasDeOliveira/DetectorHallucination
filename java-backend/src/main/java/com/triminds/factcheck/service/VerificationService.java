package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;

public interface VerificationService {
    ResponseEvaluation verify(String llmResponse);
}