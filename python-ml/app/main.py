from fastapi import FastAPI
from pydantic import BaseModel
from typing import List

app = FastAPI()

# Models
class Claim(BaseModel):
    id: str
    text: str

class Evidence(BaseModel):
    id: str
    source: str
    content: str

class VerificationResult(BaseModel):
    claim: Claim
    evidence: Evidence
    verdict: str
    confidence: float

class ResponseEvaluation(BaseModel):
    results: List[VerificationResult]
    score: float

# Endpoint
@app.post("/verify", response_model=ResponseEvaluation)
async def verify(text: str):
    # Mock: sempre retorna um claim com evidência fake
    claim = Claim(id="1", text=text)
    evidence = Evidence(id="E1", source="Wikipedia", content="Fake evidence content")
    result = VerificationResult(claim=claim, evidence=evidence, verdict="NEUTRAL", confidence=0.5)

    return ResponseEvaluation(results=[result], score=0.5)
