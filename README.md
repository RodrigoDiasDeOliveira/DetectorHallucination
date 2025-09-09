AI Hallucination Detector

The AI Hallucination Detector is a modular, scalable open-source project designed to detect hallucinations in AI-generated responses. It combines a Java backend (Spring Boot) for orchestration and a Python microservice (FastAPI) for NLP tasks, integrated with Hugging Face Transformers, Weaviate (Vector DB), and PostgreSQL for structured data storage.

Java Backend: Orchestrates requests, exposes a secure REST API (/verify), integrates with Weaviate, PostgreSQL, and the Python microservice, and handles decision logic.
Python Microservice: Performs NLP tasks (claim extraction, fact-checking, semantic scoring) using Hugging Face Transformers.
Weaviate: Stores and searches embeddings for claims and evidence.
PostgreSQL: Persists structured data (claims, evidence, verification results).
Observability: Includes logging (Logback, Python logging) and Prometheus metrics for monitoring.

The project is designed for scalability, security (JWT authentication), and observability, making it ideal for applications like chatbots, automated journalism, and educational tools.
📂 Project Structure
hallucination-detector/
├── README.md
├── LICENSE
├── docker-compose.yml
├── java-backend/
│   ├── Dockerfile
│   ├── pom.xml
│   ├── src/
│   │   ├── main/java/com/triminds/factcheck/
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   └── MetricsConfig.java
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
git clone https://github.com/RodrigoDiasDeOliveira/DetectorHallucination.git
cd hallucination-detector


Set Up EnvironmentCreate a .env file in the root directory for production secrets:
JWT_SECRET=your-very-secure-secret-key
POSTGRES_USER=user
POSTGRES_PASSWORD=password
POSTGRES_DB=factcheck



Running Locally
Python Microservice

Navigate to the python-ml directory:cd python-ml


Create and activate a virtual environment (recommended):python -m venv venv
source venv/bin/activate  # Linux/Mac
venv\Scripts\activate     # Windows


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
The Java backend uses JWT-based authentication. Obtain a token:
curl -X POST http://localhost:8081/auth/login -H "Content-Type: application/json" -d '{"username":"user","password":"password"}'

Response:
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}

Verifying a Claim
Send a POST request to the /verify endpoint with the JWT token:
curl -X POST http://localhost:8081/verify -H "Authorization: Bearer <your-jwt-token>" -H "Content-Type: application/json" -d '"The Earth is flat"'

Response Example:
{
  "claim": {
    "text": "The Earth is flat",
    "embedding": [0.1, 0.2, ...],
    "source": "LLM Response"
  },
  "evidence": {
    "text": "Scientific evidence confirms the Earth is an oblate spheroid.",
    "embedding": [0.3, 0.4, ...],
    "source": "Weaviate"
  },
  "score": 0.12,
  "verdict": {
    "isHallucination": true
  }
}

Workflow

The Java backend authenticates the request via JWT.
The VerificationService orchestrates:
Calls the Python microservice (http://python-ml:8000/verify) for claim extraction using Hugging Face Transformers.
Retrieves evidence from Weaviate using vector search.
Computes semantic similarity and performs fact-checking.
Determines if the claim is a hallucination.


Results are stored in PostgreSQL and returned to the user.

📈 Monitoring
The Java backend exposes Prometheus metrics at http://localhost:8081/actuator/prometheus. Key metrics include:

verification_requests_total: Total verification requests.
claims_extracted_total: Number of claims extracted.
hallucinations_detected_total: Number of detected hallucinations.
verification_score: Similarity score between claims and evidence.

To set up Prometheus:

Add to docker-compose.yml:prometheus:
  image: prom/prometheus:latest
  volumes:
    - ./prometheus.yml:/etc/prometheus/prometheus.yml
  ports:
    - "9090:9090"
  networks:
    - hallu-net


Create prometheus.yml:global:
  scrape_interval: 15s
scrape_configs:
  - job_name: 'java-backend'
    metrics_path: '/actuator/prometheus'
    static_configs:
      - targets: ['java-backend:8080']


Access Prometheus at http://localhost:9090.

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
Python: curl -X POST http://localhost:8000/verify -H "Content-Type: application/json" -d '"Test claim"'
Weaviate: curl http://localhost:8080/v1/.well-known/ready
PostgreSQL: psql -h localhost -U user -d factcheck

🔍 Technologies

Java Backend:
Spring Boot 3.3.4
Spring Security (JWT authentication)
DJL (Deep Java Library) for NLP
Weaviate Client (5.1.0) for vector search
PostgreSQL (JPA repositories)
Micrometer (Prometheus metrics)
Logback (logging)


Python Microservice:
FastAPI
Hugging Face Transformers (facebook/bart-large-mnli, sentence-transformers/all-MiniLM-L6-v2)
Pydantic for data validation
NumPy/SciPy for vector calculations


Infrastructure:
Weaviate (Vector DB)
PostgreSQL (structured data)
Docker Compose for orchestration



🔮 Future Enhancements

Advanced NLP: Integrate larger Transformer models (e.g., BERT, RoBERTa) for improved accuracy.
Weaviate Schema: Optimize schema for claims and evidence; populate with real datasets (e.g., Wikipedia).
Database Analytics: Add reporting and analytics for verification results in PostgreSQL.
Security: Replace in-memory user management with database-backed authentication.
Observability: Integrate Grafana for visualizing Prometheus metrics.

🤝 Contributing
Contributions are welcome! Please check the issues page for open tasks or submit a pull request. For major changes, open an issue first to discuss.
📜 License
This project is licensed under the MIT License - see the LICENSE file for details. Designed by Rodrigo Dias de Oliveira for TRIMINDS AI SOLUTIONS, with gratitude to the community and collaborators who helped shape this project.