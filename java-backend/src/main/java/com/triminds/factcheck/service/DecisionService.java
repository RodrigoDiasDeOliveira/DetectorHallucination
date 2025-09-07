package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Verdict;

public interface DecisionService {
    Verdict decide(Claim claim, Evidence evidence, double score);
}