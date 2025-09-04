package com.triminds.factcheck.model;

public class ResponseEvaluation {

    private double scoreFactuality;
    private Verdict verdict;

    public ResponseEvaluation() {}

    public ResponseEvaluation(double scoreFactuality, Verdict verdict) {
        this.scoreFactuality = scoreFactuality;
        this.verdict = verdict;
    }

    public double getScoreFactuality() {
        return scoreFactuality;
    }

    public void setScoreFactuality(double scoreFactuality) {
        this.scoreFactuality = scoreFactuality;
    }

    public Verdict getVerdict() {
        return verdict;
    }

    public void setVerdict(Verdict verdict) {
        this.verdict = verdict;
    }
}
