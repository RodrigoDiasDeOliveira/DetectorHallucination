package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;

public interface RetrievalService {
    void storeClaim(Claim claim);
    Evidence findEvidence(float[] embedding);
}