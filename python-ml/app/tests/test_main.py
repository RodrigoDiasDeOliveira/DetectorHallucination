from fastapi.testclient import TestClient
from app.main import app

client = TestClient(app)

def test_verify_endpoint():
    response = client.post("/verify", json="Sample claim")
    assert response.status_code == 200
    data = response.json()
    assert "results" in data
    assert len(data["results"]) == 1
    assert data["score"] == 0.5
