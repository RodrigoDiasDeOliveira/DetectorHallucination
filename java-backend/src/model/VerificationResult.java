package com.triminds.factcheck.model;

public class VerificationResult {
    private Claim claim;
    private Evidence evidence;
    private String verdict;
    private double confidence;

    public VerificationResult() {}

    public VerificationResult(Claim claim, Evidence evidence, String verdict, double confidence) {
        this.claim = claim;
        this.evidence = evidence;
        this.verdict = verdict;
        this.confidence = confidence;
    }

    public Claim getClaim() { return claim; }
    public Evidence getEvidence() { return evidence; }
    public String getVerdict() { return verdict; }
    public double getConfidence() { return confidence; }
}
