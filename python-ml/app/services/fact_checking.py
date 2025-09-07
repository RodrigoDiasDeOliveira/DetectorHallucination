import logging
from transformers import pipeline
from ..models.claim import Claim
from ..models.evidence import Evidence

# Configurar logging
logger = logging.getLogger(__name__)

def check_fact(claim: Claim, evidence: Evidence) -> dict:
    """
    Verifica a veracidade de um claim contra uma evidência usando implicação textual.
    
    Args:
        claim (Claim): Claim a ser verificado.
        evidence (Evidence): Evidência para comparação.
        
    Returns:
        dict: Resultado com rótulo (entailment, contradiction, neutral) e score.
    """
    logger.info(f"Verificando claim: {claim.text[:50]}... contra evidência: {evidence.text[:50]}...")
    try:
        classifier = pipeline("text-classification", model="microsoft/deberta-v3-base-tasksource-nli")
        premise = evidence.text
        hypothesis = claim.text
        result = classifier(f"{premise} {hypothesis}")
        
        label = result[0]["label"]  # Ex.: "ENTAILMENT", "CONTRADICTION", "NEUTRAL"
        score = result[0]["score"]
        
        logger.debug(f"Resultado da verificação: label={label}, score={score}")
        return {"label": label, "score": score}
    except Exception as e:
        logger.error(f"Erro ao verificar fato: {str(e)}")
        raise RuntimeError(f"Falha na verificação do fato: {str(e)}")