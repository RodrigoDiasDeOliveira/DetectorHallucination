package com.triminds.factcheck.service.impl;

import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.service.RetrievalService;
import io.weaviate.client.WeaviateClient;
import io.weaviate.client.v1.data.model.WeaviateObject;
import io.weaviate.client.v1.graphql.query.argument.NearVectorArgument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RetrievalServiceImpl implements RetrievalService {
    @Autowired
    private WeaviateClient weaviateClient;

    @Override
    public void storeClaim(Claim claim) {
        WeaviateObject obj = WeaviateObject.builder()
            .className("Claims")
            .properties(Map.of("text", claim.getText(), "embedding", claim.getEmbedding()))
            .build();
        weaviateClient.data().creator().withObject(obj).run();
    }

    @Override
    public Evidence findEvidence(float[] embedding) {
        NearVectorArgument nearVector = NearVectorArgument.builder()
            .vector(embedding)
            .certainty(0.8f)
            .build();
        var result = weaviateClient.graphQL().get()
            .withClassName("Evidences")
            .withNearVector(nearVector)
            .withFields(Field.builder().name("text").build())
            .run();
        // Processar resultado (simplificado)
        Evidence evidence = new Evidence();
        evidence.setText("Evidência mockada");
        return evidence;
    }
}