package com.triminds.factcheck.model;

public class VerificationResult {
    private Claim claim;
    private Evidence evidence;
    private String verdict;
    private double score;

    public VerificationResult() {}

    public VerificationResult(Claim claim, Evidence evidence, String verdict, double score) {
        this.claim = claim;
        this.evidence = evidence;
        this.verdict = verdict;
        this.score = score;
    }

    public Claim getClaim() { return claim; }
    public void setClaim(Claim claim) { this.claim = claim; }

    public Evidence getEvidence() { return evidence; }
    public void setEvidence(Evidence evidence) { this.evidence = evidence; }

    public String getVerdict() { return verdict; }
    public void setVerdict(String verdict) { this.verdict = verdict; }

    public double getScore() { return score; }
    public void setScore(double score) { this.score = score; }
}
