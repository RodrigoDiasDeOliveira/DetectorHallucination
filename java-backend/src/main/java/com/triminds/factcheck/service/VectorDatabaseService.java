package com.triminds.factcheck.service;

import org.springframework.stereotype.Service;

@Service
public class VectorDatabaseService {

    private final String weaviateEndpoint;

    public VectorDatabaseService(String weaviateEndpoint) {
        this.weaviateEndpoint = weaviateEndpoint;
    }

    public void saveEmbedding(String id, float[] embedding) {
        System.out.println("Saving embedding to Weaviate at " + weaviateEndpoint);
        // TODO: Implementar chamada real
    }
}
