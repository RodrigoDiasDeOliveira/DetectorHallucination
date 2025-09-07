AI Hallucination Detector
AI Hallucination Detector is a modular, scalable project designed to detect hallucinations in AI-generated responses. It combines a Java backend (Spring Boot) for orchestration and a Python microservice (FastAPI) for NLP tasks, integrated with Hugging Face Transformers, Weaviate (Vector DB), and PostgreSQL for structured data storage.

Java Backend: Orchestrates requests, exposes a secure REST API (/verify), integrates with Weaviate, PostgreSQL, and the Python microservice, and handles decision logic.
Python Microservice: Performs NLP tasks (claim extraction, fact-checking, semantic scoring) using Hugging Face Transformers.
Weaviate: Stores and searches embeddings for claims and evidence.
PostgreSQL: Persists structured data (claims, evidence, verification results).

The project is designed for scalability, security (JWT authentication), and observability (logging and Prometheus metrics).

📂 Project Structure
hallucination-detector/
├── README.md
├── docker-compose.yml
├── java-backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── src/
│   │   ├── main/java/com/triminds/factcheck/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   └── JwtAuthenticationFilter.java
│   │   │   ├── controller/
│   │   │   │   ├── VerificationController.java
│   │   │   │   └── AuthController.java
│   │   │   ├── FactCheckApplication.java
│   │   │   ├── model/
│   │   │   │   ├── Claim.java
│   │   │   │   ├── Evidence.java
│   │   │   │   ├── ResponseEvaluation.java
│   │   │   │   ├── Verdict.java
│   │   │   │   └── VerificationResult.java
│   │   │   ├── repository/
│   │   │   │   ├── ClaimRepository.java
│   │   │   │   ├── EvidenceRepository.java
│   │   │   │   └── VerificationResultRepository.java
│   │   │   ├── service/
│   │   │   │   ├── ClaimExtractionService.java
│   │   │   │   ├── DecisionService.java
│   │   │   │   ├── RetrievalService.java
│   │   │   │   ├── ScoringService.java
│   │   │   │   ├── VerificationService.java
│   │   │   │   └── impl/
│   │   │   │       └── VerificationServiceImpl.java
│   │   │   └── client/
│   │   │       └── PythonClient.java
│   │   └── test/java/com/triminds/factcheck/
│   │       ├── service/
│   │       │   ├── VerificationServiceTest.java
│   │       │   ├── ScoringServiceTest.java
│   │       │   └── DecisionServiceTest.java
│   │       └── controller/
│   │           └── VerificationControllerTest.java
└── python-ml/
    ├── Dockerfile
    ├── requirements.txt
    └── app/
        ├── main.py
        ├── models/
        │   ├── claim.py
        │   └── evidence.py
        ├── routers/
        │   └── verify.py
        ├── services/
        │   ├── claim_extraction.py
        │   ├── fact_checking.py
        │   └── semantic_scoring.py
        └── tests/
            ├── test_main.py
            └── test_services.py

🚀 Getting Started
Prerequisites

Docker and Docker Compose for containerized deployment.
Java 21 and Maven for local Java development.
Python 3.11 and pip for local Python development.
curl or Postman for testing API endpoints.

Installation

Clone the Repository
git clone <you know how to do (repository-url)>
cd hallucination-detector


Set Up Environment

Create a .env file in the root directory (optional for production secrets):JWT_SECRET=your-very-secure-secret-key





Running Locally
Python Microservice

Navigate to the python-ml directory:cd python-ml


Install dependencies:pip install -r requirements.txt


Run the FastAPI server:uvicorn app.main:app --reload --host 0.0.0.0 --port 8000



Java Backend

Navigate to the java-backend directory:cd java-backend


Run the Spring Boot application:mvn spring-boot:run



Running with Docker

Build and start all services:docker-compose up --build


Access services:
Python Microservice: http://localhost:8000/verify
Java Backend: http://localhost:8081/verify (requires JWT authentication)
Weaviate: http://localhost:8080/v1/.well-known/ready
PostgreSQL: psql -h localhost -U user -d factcheck



🔄 Usage
Authentication
The Java backend uses JWT-based authentication. Obtain a token by sending a POST request to /auth/login:
curl -X POST http://localhost:8081/auth/login -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

Verifying a Claim
Send a POST request to the /verify endpoint with the JWT token:
curl -X POST http://localhost:8081/verify -H "Authorization: Bearer <your-jwt-token>" -H "Content-Type: application/json" -d '"Sample claim text"'

Response Example:
{
  "claim": {
    "text": "Sample claim text",
    "embedding": [...],
    "source": "LLM Response"
  },
  "evidence": {
    "text": "Relevant evidence text",
    "embedding": [...],
    "source": "Mock Source"
  },
  "score": 0.95,
  "verdict": {
    "isHallucination": false
  }
}

Workflow

The Java backend receives the request and authenticates it via JWT.
The VerificationService orchestrates:
Calls the Python microservice (http://python-ml:8000/verify) for claim extraction.
Retrieves evidence from Weaviate.
Computes semantic similarity and fact-checking.
Determines if the claim is a hallucination.


Results are stored in PostgreSQL and returned to the user.

🧪 Testing
Java Backend
Run unit and integration tests:
cd java-backend
mvn test

Tests include:

Unit Tests: VerificationServiceTest, ScoringServiceTest, DecisionServiceTest
Controller Tests: VerificationControllerTest (using MockMvc)

Python Microservice
Run tests with pytest:
cd python-ml/app
pytest tests/

Tests include:

Unit Tests: test_services.py (for claim_extraction, fact_checking, semantic_scoring)
API Tests: test_main.py (using FastAPI TestClient)

🐳 Docker Orchestration
The docker-compose.yml orchestrates four services:

java-backend: Spring Boot application (port 8081:8080).
python-ml: FastAPI microservice (port 8000:8000).
postgres: PostgreSQL database (port 5432:5432).
weaviate: Vector DB for embeddings (port 8080:8080).

To build and run:
docker-compose up --build

Check service health:

Java: curl http://localhost:8081/actuator/health
Python: curl http://localhost:8000/verify -H "Content-Type: application/json" -d '"Test claim"'
Weaviate: curl http://localhost:8080/v1/.well-known/ready
PostgreSQL: psql -h localhost -U user -d factcheck

🔍 Technologies

Java Backend:
Spring Boot 3.3.4
Spring Security (JWT authentication)
DJL (Deep Java Library) for NLP
Weaviate Client for vector search
PostgreSQL (JPA repositories)
Micrometer (Prometheus metrics)
Logback (logging)


Python Microservice:
FastAPI
Hugging Face Transformers (facebook/bart-large-mnli, sentence-transformers/all-MiniLM-L6-v2)
Pydantic for data validation


Infrastructure:
Weaviate (Vector DB)
PostgreSQL (structured data)
Docker Compose for orchestration



🔮 Future Enhancements

Advanced NLP: Integrate larger Transformer models (e.g., BERT, RoBERTa) for improved claim extraction and fact-checking.
Weaviate Schema: Fully implement and optimize the Weaviate schema for claims and evidence.
Database Analytics: Add reporting and analytics for verification results in PostgreSQL.
Security: Replace in-memory user management with database-backed authentication.
Observability: Integrate Grafana for visualizing Prometheus metrics.

📜 License
This project was designed for TRIMINDS AI SOLUTIONS, by Rodrigo Dias de Oliveira using every tecnology free in the web, i hope it inspiring you...thanks to the group of friends who help me...