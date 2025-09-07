package com.triminds.factcheck.service.impl;

import ai.djl.huggingface.tokenizers.HuggingFaceTokenizer;
import ai.djl.modality.Classifications;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.service.ClaimExtractionService;
import org.springframework.stereotype.Service;

@Service
public class ClaimExtractionServiceImpl implements ClaimExtractionService {
    @Override
    public Claim extractClaim(String text) {
        Criteria<String, Classifications> criteria = Criteria.builder()
            .optEngine("PyTorch")
            .optArtifactId("deberta-v3-base-tasksource-nli")
            .setTypes(String.class, Classifications.class)
            .build();

        try (ZooModel<String, Classifications> model = criteria.loadModel()) {
            Classifications result = model.newPredictor().predict(text);
            Claim claim = new Claim();
            claim.setText(text);
            claim.setEmbedding(generateEmbedding(text));
            claim.setSource("LLM Response");
            return claim;
        } catch (Exception e) {
            throw new RuntimeException("Erro ao extrair claim", e);
        }
    }

    private float[] generateEmbedding(String text) {
        // Implementar com DJL (exemplo simplificado)
        return new float[]{/* embedding */};
    }
}