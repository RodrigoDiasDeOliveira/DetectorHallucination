package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.VerificationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ScoringServiceTest {

    @Test
    void calculateScore_returnsExpectedValue() {
        Claim claim = new Claim("1", "Test");
        Evidence evidence = new Evidence("E1", "Source", "Content");
        VerificationResult result = new VerificationResult(claim, evidence, "SUPPORTED", 0.8);

        ScoringService scoringService = new ScoringService();
        double score = scoringService.calculateScore(List.of(result));

        assertEquals(0.8, score);
    }
}
