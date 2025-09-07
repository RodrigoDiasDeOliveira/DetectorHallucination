package com.triminds.factcheck.repository;

import com.triminds.factcheck.model.VerificationResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationResultRepository extends JpaRepository<VerificationResult, Long> {
}