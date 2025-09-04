package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import java.util.List;

public interface ClaimExtractionService {
    List<Claim> extractClaims(String responseText);
}
