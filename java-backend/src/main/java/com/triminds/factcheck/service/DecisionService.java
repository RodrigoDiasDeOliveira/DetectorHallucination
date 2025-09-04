package com.triminds.factcheck.service;

import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.model.Verdict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DecisionService {

    @Autowired
    private ScoringService scoringService;

    public ResponseEvaluation evaluate(String claimText) {
        // Aqui você pode usar lógica real para decidir o veredicto
        Verdict verdict = Verdict.SUPPORT; // Exemplo fixo
        double score = scoringService.scoreClaim(verdict);

        // Cria o objeto ResponseEvaluation com o score e veredicto
        ResponseEvaluation evaluation = new ResponseEvaluation();
        evaluation.setVerdict(verdict);
        evaluation.setScoreFactuality(score);

        return evaluation;
    }
}
