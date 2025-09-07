from fastapi import APIRouter
from ..services.claim_extraction import extract_claim
from ..services.fact_checking import check_fact
from ..services.semantic_scoring import calculate_semantic_score
from ..models.claim import Claim
from ..models.evidence import Evidence

router = APIRouter()

@router.post("/verify")
async def verify(text: str):
    # Extrair claim
    claim = extract_claim(text)
    
    # Evidência mockada (substituir por chamada ao Weaviate no futuro)
    evidence = Evidence(
        text="Mocked evidence for testing",
        embedding=[0.0] * 768,
        source="Mock Source"
    )
    
    # Verificar fato
    fact_result = check_fact(claim, evidence)
    
    # Calcular similaridade
    similarity = calculate_semantic_score(claim, evidence)
    
    return {
        "claim": claim.dict(),
        "evidence": evidence.dict(),
        "fact_check": fact_result,
        "similarity_score": similarity
    }