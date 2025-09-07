package com.triminds.factcheck.service;

public interface ScoringService {
    double calculateScore(String claim, String evidence);
}
