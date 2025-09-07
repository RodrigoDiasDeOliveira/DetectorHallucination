package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.VerificationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DecisionServiceTest {

    @Test
    void decide_returnsApprovedOrReview() {
        Claim claim = new Claim("1", "Test");
        Evidence evidence = new Evidence("E1", "Source", "Content");
        VerificationResult result = new VerificationResult(claim, evidence, "SUPPORTED", 1.0);
        ResponseEvaluation evaluation = new ResponseEvaluation(List.of(result), 1.0);

        DecisionService service = new DecisionService();
        String decision = service.decide(evaluation);

        assertEquals("APPROVED", decision);
    }
}
