package com.triminds.factcheck.repository;

import com.triminds.factcheck.model.Claim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClaimRepository extends JpaRepository<Claim, Long> {
}
