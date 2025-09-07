package com.triminds.factcheck.service;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.VerificationResult;
import org.springframework.stereotype.Service;

@Service
public class VerificationService {

    private final HuggingFaceService huggingFaceService;
    private final VectorDatabaseService vectorDatabaseService;

    public VerificationService(HuggingFaceService huggingFaceService,
                               VectorDatabaseService vectorDatabaseService) {
        this.huggingFaceService = huggingFaceService;
        this.vectorDatabaseService = vectorDatabaseService;
    }

    public VerificationResult verify(String claimText) {
        // 1️⃣ Criar objeto Claim
        Claim claim = new Claim("claim-1", claimText);

        // 2️⃣ Gerar embedding via Hugging Face
        String embedding = huggingFaceService.getEmbedding(claimText);

        // 3️⃣ Salvar embedding no Vector Database
        vectorDatabaseService.saveEmbedding(claim.getId(), parseEmbedding(embedding));

        // 4️⃣ Criar Evidence fake por enquanto (placeholder)
        Evidence evidence = new Evidence("ev-1", "https://example.com", "Supporting evidence placeholder");

        // 5️⃣ Retornar resultado inicial
        return new VerificationResult(claim, evidence, "PROCESSING", 0.0);
    }

    private float[] parseEmbedding(String embeddingString) {
        // 🔧 Simulação simples até implementarmos a resposta real da HF API
        return new float[]{0.1f, 0.2f, 0.3f};
    }
}
