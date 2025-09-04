package com.triminds.factcheck.model;

import java.util.List;

public class ResponseEvaluation {
    private List<VerificationResult> results;
    private double score;

    public ResponseEvaluation() {}

    public ResponseEvaluation(List<VerificationResult> results, double score) {
        this.results = results;
        this.score = score;
    }

    public List<VerificationResult> getResults() { return results; }
    public double getScore() { return score; }
}
