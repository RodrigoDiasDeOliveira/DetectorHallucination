package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.VerificationResult;
import com.triminds.factcheck.model.Verdict;

import java.util.ArrayList;
import java.util.List;

public class VerificationService {

    private final RetrievalService retrievalService;

    public VerificationService(RetrievalService retrievalService) {
        this.retrievalService = retrievalService;
    }

    public List<VerificationResult> verifyClaims(List<Claim> claims) {
        List<VerificationResult> results = new ArrayList<>();
        for (Claim claim : claims) {
            List<Evidence> evidences = retrievalService.retrieveEvidence(claim.getText());
            for (Evidence e : evidences) {
                results.add(new VerificationResult(claim, e, Verdict.NEUTRAL.name(), 0.5));
            }
        }
        return results;
    }
}
