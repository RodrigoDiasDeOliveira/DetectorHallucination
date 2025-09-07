package com.triminds.factcheck.service.impl;

import com.triminds.factcheck.service.ScoringService;
import org.springframework.stereotype.Service;

@Service
public class ScoringServiceImpl implements ScoringService {
    @Override
    public double calculateScore(String claim, String evidence) {
        // Usar DJL para calcular similaridade de cosseno
        return 0.9; // Placeholder
    }
}