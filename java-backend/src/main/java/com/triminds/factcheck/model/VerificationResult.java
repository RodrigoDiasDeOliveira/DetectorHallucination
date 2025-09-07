package com.triminds.factcheck.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class VerificationResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long claimId;
    private double score;
    private boolean isHallucination;
    private String timestamp;

    // Getters e Setters
}