package com.triminds.factcheck.service.impl;

import com.triminds.factcheck.client.PythonClient;
import com.triminds.factcheck.model.Claim;
import com.triminds.factcheck.model.Evidence;
import com.triminds.factcheck.model.ResponseEvaluation;
import com.triminds.factcheck.model.VerificationResult;
import com.triminds.factcheck.model.Verdict;
import com.triminds.factcheck.repository.ClaimRepository;
import com.triminds.factcheck.repository.EvidenceRepository;
import com.triminds.factcheck.repository.VerificationResultRepository;
import com.triminds.factcheck.service.ClaimExtractionService;
import com.triminds.factcheck.service.DecisionService;
import com.triminds.factcheck.service.RetrievalService;
import com.triminds.factcheck.service.ScoringService;
import com.triminds.factcheck.service.VerificationService;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VerificationServiceImpl implements VerificationService {
    private static final Logger logger = LoggerFactory.getLogger(VerificationServiceImpl.class);

    @Autowired
    private ClaimExtractionService claimExtractionService;

    @Autowired
    private RetrievalService retrievalService;

    @Autowired
    private ScoringService scoringService;

    @Autowired
    private DecisionService decisionService;

    @Autowired
    private ClaimRepository claimRepository;

    @Autowired
    private EvidenceRepository evidenceRepository;

    @Autowired
    private VerificationResultRepository verificationResultRepository;

    @Autowired
    private PythonClient pythonClient;

    @Autowired
    private MeterRegistry meterRegistry;

    @Override
    public ResponseEvaluation verify(String llmResponse) {
        logger.info("Iniciando verificação para resposta: {}", llmResponse);
        meterRegistry.counter("verification.requests.total").increment();

        // 1. Extrair claim da resposta do LLM
        Claim claim;
        try {
            claim = claimExtractionService.extractClaim(llmResponse);
            claimRepository.save(claim);
            logger.debug("Claim extraído e salvo: {}", claim.getText());
            meterRegistry.counter("claims.extracted").increment();
        } catch (Exception e) {
            logger.error("Erro ao extrair claim: {}", e.getMessage());
            meterRegistry.counter("verification.errors", "type", "claim_extraction").increment();
            throw new RuntimeException("Falha na extração do claim", e);
        }

        // 2. Opcionalmente, chamar o microsserviço Python
        try {
            String pythonResult = pythonClient.callPythonService(llmResponse);
            logger.debug("Resposta do microsserviço Python: {}", pythonResult);
            meterRegistry.counter("python.service.calls").increment();
        } catch (Exception e) {
            logger.warn("Falha ao chamar microsserviço Python, continuando com extração local: {}", e.getMessage());
            meterRegistry.counter("python.service.errors").increment();
        }

        // 3. Recuperar evidência do Weaviate
        Evidence evidence;
        try {
            evidence = retrievalService.findEvidence(claim.getEmbedding());
            evidenceRepository.save(evidence);
            logger.debug("Evidência recuperada e salva: {}", evidence.getText());
            meterRegistry.counter("evidence.retrieved").increment();
        } catch (Exception e) {
            logger.error("Erro ao recuperar evidência: {}", e.getMessage());
            meterRegistry.counter("verification.errors", "type", "evidence_retrieval").increment();
            throw new RuntimeException("Falha na recuperação de evidência", e);
        }

        // 4. Calcular score de similaridade
        double score;
        try {
            score = scoringService.calculateScore(claim.getText(), evidence.getText());
            logger.debug("Score calculado: {}", score);
            meterRegistry.gauge("verification.score", score);
        } catch (Exception e) {
            logger.error("Erro ao calcular score: {}", e.getMessage());
            meterRegistry.counter("verification.errors", "type", "scoring").increment();
            throw new RuntimeException("Falha no cálculo do score", e);
        }

        // 5. Tomar decisão sobre alucinação
        Verdict verdict;
        try {
            verdict = decisionService.decide(claim, evidence, score);
            logger.debug("Veredicto determinado: isHallucination={}", verdict.isHallucination());
            if (verdict.isHallucination()) {
                meterRegistry.counter("hallucinations.detected").increment();
            }
        } catch (Exception e) {
            logger.error("Erro ao determinar veredicto: {}", e.getMessage());
            meterRegistry.counter("verification.errors", "type", "decision").increment();
            throw new RuntimeException("Falha na tomada de decisão", e);
        }

        // 6. Salvar resultado da verificação
        VerificationResult result = new VerificationResult();
        result.setClaimId(claim.getId());
        result.setScore(score);
        result.setIsHallucination(verdict.isHallucination());
        result.setTimestamp(LocalDateTime.now().toString());
        verificationResultRepository.save(result);
        logger.debug("Resultado da verificação salvo: claimId={}, score={}", result.getClaimId(), result.getScore());

        // 7. Construir resposta final
        ResponseEvaluation evaluation = new ResponseEvaluation();
        evaluation.setClaim(claim);
        evaluation.setEvidence(evidence);
        evaluation.setScore(score);
        evaluation.setVerdict(verdict);

        logger.info("Verificação concluída com sucesso para claim: {}", claim.getText());
        return evaluation;
    }
}