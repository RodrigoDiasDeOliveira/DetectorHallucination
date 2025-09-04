package com.triminds.factcheck.repository;

import com.triminds.factcheck.model.Evidence;
import java.util.List;

public interface EvidenceRepository {
    List<Evidence> findEvidenceForClaim(String claimText);
}
