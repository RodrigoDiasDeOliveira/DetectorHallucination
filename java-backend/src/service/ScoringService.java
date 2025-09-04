package com.triminds.factcheck.service;

import com.triminds.factcheck.model.VerificationResult;

import java.util.List;

public class ScoringService {

    public double calculateScore(List<VerificationResult> results) {
        if (results.isEmpty()) return 0.0;

        double sum = 0;
        for (VerificationResult r : results) {
            if (r.getVerdict() == com.triminds.factcheck.model.Verdict.SUPPORTED) sum += r.getConfidence();
        }
        return sum / results.size();
    }
}
