package com.triminds.factcheck.service.impl;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.Verdict;
import com.triminds.factcheck.service.DecisionService;
import org.springframework.stereotype.Service;

@Service
public class DecisionServiceImpl implements DecisionService {
    @Override
    public Verdict decide(Claim claim, Evidence evidence, double score) {
        Verdict verdict = new Verdict();
        verdict.setIsHallucination(score < 0.8);
        verdict.setExplanation("Score abaixo do threshold indica possível alucinação");
        return verdict;
    }
}