package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.VerificationResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class VerificationServiceTest {

    @Test
    void verifyClaims_returnsMockedResults() {
        RetrievalService mockRetrieval = mock(RetrievalService.class);
        Claim claim = new Claim("1", "Test claim");
        Evidence evidence = new Evidence("E1", "Source", "Content");
        when(mockRetrieval.retrieveEvidence(anyString())).thenReturn(List.of(evidence));

        VerificationService service = new VerificationService(mockRetrieval);
        List<VerificationResult> results = service.verifyClaims(List.of(claim));

        assertEquals(1, results.size());
        assertEquals("NEUTRAL", results.get(0).getVerdict());
        assertEquals("E1", results.get(0).getEvidence().getId());
    }
}
