from app.services import SomeServiceFunction  # exemplo de função de serviço
from ..services.claim_extraction import extract_claim
from ..models.claim import Claim

def test_service_logic():
    result = SomeServiceFunction("input text")
    assert result is not None
    # adicionar mais asserts dependendo da lógica

def test_extract_claim():
    text = "The Earth is flat"
    claim = extract_claim(text)
    assert isinstance(claim, Claim)
    assert claim.text == text