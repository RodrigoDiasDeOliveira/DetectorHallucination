package com.triminds.factcheck.service;

import com.triminds.factcheck.model.VerificationResult;
import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.Verdict;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    public VerificationResult verify(String claimText) {
        Claim claim = new Claim(claimText);
        Evidence evidence = new Evidence("Some evidence text");

        String verdictText = "SUPPORT"; // exemplo
        double score = 0.9;

        return new VerificationResult(claim, evidence, verdictText, score);
    }
}
