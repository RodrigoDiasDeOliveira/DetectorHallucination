package com.triminds.factcheck.service.impl;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.model.Verdict;
import com.triminds.factcheck.repository.ClaimRepository;
import com.triminds.factcheck.repository.EvidenceRepository;
import com.triminds.factcheck.repository.VerificationResultRepository;
import com.triminds.factcheck.service.ClaimExtractionService;
import com.triminds.factcheck.service.DecisionService;
import com.triminds.factcheck.service.RetrievalService;
import com.triminds.factcheck.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrchestrationServiceImpl implements OrchestrationService {
    @Autowired
    private ClaimExtractionService claimExtractionService;
    @Autowired
    private RetrievalService retrievalService;
    @Autowired
    private ScoringService scoringService;
    @Autowired
    private DecisionService decisionService;
    @Autowired
    private ClaimRepository claimRepository;
    @Autowired
    private EvidenceRepository evidenceRepository;
    @Autowired
    private VerificationResultRepository verificationResultRepository;

    @Override
    public ResponseEvaluation verify(String llmResponse) {
        Claim claim = claimExtractionService.extractClaim(llmResponse);
        claimRepository.save(claim);

        Evidence evidence = retrievalService.findEvidence(claim.getEmbedding());
        evidenceRepository.save(evidence);

        double score = scoringService.calculateScore(claim.getText(), evidence.getText());
        Verdict verdict = decisionService.decide(claim, evidence, score);

        VerificationResult result = new VerificationResult();
        result.setClaimId(claim.getId());
        result.setScore(score);
        result.setIsHallucination(verdict.isHallucination());
        result.setTimestamp(LocalDateTime.now().toString());
        verificationResultRepository.save(result);

        ResponseEvaluation evaluation = new ResponseEvaluation();
        evaluation.setClaim(claim);
        evaluation.setEvidence(evidence);
        evaluation.setScore(score);
        evaluation.setVerdict(verdict);

        return evaluation;
    }
}