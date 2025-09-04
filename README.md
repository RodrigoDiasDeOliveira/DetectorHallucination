AI Hallucination Detector

AI Hallucination Detector is an experimental project combining Java (Spring Boot) and Python (FastAPI) to detect hallucinations in AI responses.

Java Backend → Orchestrates requests, exposes REST API /verify, integrates with services and decision logic.

Python Microservice → Handles NLP-based claim extraction, evidence retrieval, and verification (currently mocked).

The project is modular, scalable, and ready for real NLP integration, vector DB, and fact-checking.

📂 Project Structure
hallucination-detector/
├── README.md
├── docker-compose.yml
├── java-backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── src/
│   │   ├── config/
│   │   ├── controller/VerificationController.java
│   │   ├── main/java/com/triminds/factcheck/FactCheckApplication.java
│   │   ├── model/
│   │   │   ├── Claim.java
│   │   │   ├── Evidence.java
│   │   │   ├── ResponseEvaluation.java
│   │   │   ├── Verdict.java
│   │   │   └── VerificationResult.java
│   │   ├── repository/EvidenceRepository.java
│   │   └── service/
│   │       ├── ClaimExtractionService.java
│   │       ├── DecisionService.java
│   │       ├── OrchestrationService.java
│   │       ├── RetrievalService.java
│   │       ├── ScoringService.java
│   │       └── VerificationService.java
│   └── src/test/java/com/triminds/factcheck/
│       ├── service/
│       │   ├── VerificationServiceTest.java
│       │   ├── ScoringServiceTest.java
│       │   └── DecisionServiceTest.java
│       └── controller/VerificationControllerTest.java
└── python-ml/
    ├── Dockerfile
    ├── requirements.txt
    └── app/
        ├── config/
        ├── main.py
        ├── models/
        ├── routers/
        ├── services/
        └── tests/
            ├── test_main.py
            └── test_services.py

🚀 Getting Started
1️⃣ Python Microservice
cd python-ml
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000

2️⃣ Java Backend
cd java-backend
mvn spring-boot:run

🔄 Usage

Send a POST request to the Java backend:

curl -X POST http://localhost:8080/verify -H "Content-Type: application/json" -d "\"Sample claim text\""


Java backend calls Python microservice /verify.

Python returns claims & evidence (mocked).

Java evaluates response reliability using ScoringService + DecisionService.

Final ResponseEvaluation returned to user.

🧪 Testing
Java Backend

Run tests with Maven:

cd java-backend
mvn test


Unit tests: VerificationServiceTest, ScoringServiceTest, DecisionServiceTest

Controller tests: VerificationControllerTest (MockMvc simulates API calls)

Python Microservice

Run tests with pytest:

cd python-ml/app
pytest tests/


Unit tests for services: test_services.py

API tests for endpoints: test_main.py (FastAPI TestClient)

🐳 Docker & Orchestration

To run both services together using Docker:

1️⃣ Build and start containers
docker-compose up --build

2️⃣ Access services

Python Microservice → http://localhost:8000/verify

Java Backend → http://localhost:8080/verify

Note: In Docker Compose, the Java backend calls Python at http://python-ml:8000/verify.

🔮 Future Enhancements

Hugging Face Transformers

Pre-trained NLP models for claim extraction, fact-checking, semantic scoring.

Vector Database (Vector DB)

Store and search embeddings of claims & evidence efficiently.

Candidates: Weaviate, Pinecone, Milvus.

PostgreSQL / Oracle Database

Store structured data: claims, evidence, verification results, logs.

Supports analytics, reporting, and observability.