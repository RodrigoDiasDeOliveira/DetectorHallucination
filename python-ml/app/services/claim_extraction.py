import logging
from transformers import pipeline
from ..models.claim import Claim

# Configurar logging
logger = logging.getLogger(__name__)

def extract_claim(text: str) -> Claim:
    """
    Extrai um claim de um texto usando zero-shot classification do Hugging Face.
    
    Args:
        text (str): Resposta do LLM a ser analisada.
        
    Returns:
        Claim: Objeto com texto, embedding e fonte.
    """
    logger.info(f"Extraindo claim do texto: {text[:50]}...")
    try:
        classifier = pipeline("zero-shot-classification", model="facebook/bart-large-mnli")
        result = classifier(text, candidate_labels=["factual", "possible hallucination", "neutral"])
        
        # Placeholder para embedding (substituir por modelo real, ex.: sentence-transformers)
        embedding = [0.0] * 768  # Exemplo: 768 dimensões para compatibilidade com sentence-transformers
        
        claim = Claim(
            text=text,
            embedding=embedding,
            source="LLM Response"
        )
        logger.debug(f"Claim extraído: label={result['labels'][0]}, score={result['scores'][0]}")
        return claim
    except Exception as e:
        logger.error(f"Erro ao extrair claim: {str(e)}")
        raise RuntimeError(f"Falha na extração do claim: {str(e)}")