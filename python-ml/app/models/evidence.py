from pydantic import BaseModel
from typing import List

class Evidence(BaseModel):
    text: str
    embedding: List[float]
    source: str