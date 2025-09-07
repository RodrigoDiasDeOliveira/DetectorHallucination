from pydantic import BaseModel

class Claim(BaseModel):
    text: str
    embedding: list[float]
    source: str