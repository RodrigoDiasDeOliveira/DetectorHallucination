import logging
import numpy as np
from transformers import AutoModel, AutoTokenizer
from scipy.spatial.distance import cosine
from ..models.claim import Claim
from ..models.evidence import Evidence

# Configurar logging
logger = logging.getLogger(__name__)

def calculate_semantic_score(claim: Claim, evidence: Evidence) -> float:
    """
    Calcula a similaridade semântica entre um claim e uma evidência usando embeddings.
    
    Args:
        claim (Claim): Claim a ser comparado.
        evidence (Evidence): Evidência para comparação.
        
    Returns:
        float: Score de similaridade (0 a 1, onde 1 é idêntico).
    """
    logger.info(f"Calculando similaridade semântica: claim={claim.text[:50]}..., evidence={evidence.text[:50]}...")
    try:
        # Carregar modelo e tokenizer
        model_name = "sentence-transformers/all-MiniLM-L6-v2"
        tokenizer = AutoTokenizer.from_pretrained(model_name)
        model = AutoModel.from_pretrained(model_name)

        # Gerar embeddings
        def get_embedding(text: str) -> np.ndarray:
            inputs = tokenizer(text, return_tensors="pt", truncation=True, padding=True)
            outputs = model(**inputs)
            return outputs.last_hidden_state.mean(dim=1).detach().numpy().flatten()

        claim_embedding = get_embedding(claim.text)
        evidence_embedding = get_embedding(evidence.text)

        # Calcular similaridade de cosseno
        similarity = 1 - cosine(claim_embedding, evidence_embedding)
        
        logger.debug(f"Similaridade calculada: {similarity}")
        return float(similarity)
    except Exception as e:
        logger.error(f"Erro ao calcular similaridade semântica: {str(e)}")
        raise RuntimeError(f"Falha no cálculo da similaridade semântica: {str(e)}")