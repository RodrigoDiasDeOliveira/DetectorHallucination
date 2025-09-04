package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Evidence;
import java.util.List;

public interface RetrievalService {
    List<Evidence> retrieveEvidence(String claimText);
}
