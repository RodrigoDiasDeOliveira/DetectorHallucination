package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Verdict;
import org.springframework.stereotype.Service;

@Service
public class ScoringService {

    public double scoreClaim(Verdict verdict) {
        if (verdict == Verdict.SUPPORT) {
            return 1.0;
        } else if (verdict == Verdict.CONTRADICT) {
            return 0.0;
        }
        return 0.5; // neutro
    }
}
