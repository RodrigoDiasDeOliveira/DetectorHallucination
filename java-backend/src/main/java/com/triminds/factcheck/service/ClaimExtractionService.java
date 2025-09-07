package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;

public interface ClaimExtractionService {
    Claim extractClaim(String text);
}