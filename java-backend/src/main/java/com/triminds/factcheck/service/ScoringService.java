package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.Verdict;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public Verdict calculateVerdict(Claim claim, Evidence evidence) {
        // TODO: implementar scoring real usando embeddings
        // Por enquanto, placeholder simples
        if (evidence.getText() == null || evidence.getText().isEmpty()) {
            return Verdict.INCONCLUSIVE;
        }
        return Verdict.SUPPORT;
    }
}
