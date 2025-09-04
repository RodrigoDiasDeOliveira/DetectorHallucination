package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;

public class DecisionService {

    private static final double THRESHOLD = 0.7;

    public String decide(ResponseEvaluation evaluation) {
        if (evaluation.getScoreFactuality() >= THRESHOLD) return "APPROVED";
        return "REVIEW";
    }
}
