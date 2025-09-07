package com.triminds.factcheck.repository;

import com.triminds.factcheck.model.Evidence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EvidenceRepository extends JpaRepository<Evidence, Long> {
}