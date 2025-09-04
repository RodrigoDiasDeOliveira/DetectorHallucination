AI Hallucination Detector

AI Hallucination Detector is an experimental project combining Java (Spring Boot) and Python (FastAPI) to detect hallucinations in AI responses.

Java Backend → Orchestrates requests, exposes REST API /verify, integrates with services and decision logic.

Python Microservice → Handles NLP-based claim extraction, evidence retrieval, and verification (currently mocked).

The project is modular, scalable, and ready for real NLP integration, vector DB, and fact-checking.

📂 Project Structure
hallucination-detector/
├── README.md
├── java-backend/
│   ├── pom.xml
│   └── src/
│       ├── config/
│       ├── controller/VerificationController.java
│       ├── main/java/com/triminds/factcheck/FactCheckApplication.java
│       ├── model/
│       │   ├── Claim.java
│       │   ├── Evidence.java
│       │   ├── ResponseEvaluation.java
│       │   ├── Verdict.java
│       │   └── VerificationResult.java
│       ├── repository/EvidenceRepository.java
│       └── service/
│           ├── ClaimExtractionService.java
│           ├── DecisionService.java
│           ├── OrchestrationService.java
│           ├── RetrievalService.java
│           ├── ScoringService.java
│           └── VerificationService.java
└── python-ml/
    ├── Dockerfile
    ├── app/
    │   ├── config/
    │   ├── main.py
    │   ├── models/
    │   ├── routers/
    │   └── services/
    └── requirements.txt

🚀 Getting Started
1️⃣ Python Microservice

Install dependencies and start the FastAPI server:

cd python-ml
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8000

2️⃣ Java Backend

Start Spring Boot:

cd java-backend
mvn spring-boot:run

🔄 Usage

Send a POST request to the Java backend:

curl -X POST http://localhost:8080/verify -H "Content-Type: application/json" -d "\"Sample claim text\""


Java backend calls Python microservice /verify.

Python returns claims & evidence (mocked for now).

Java evaluates response reliability using ScoringService + DecisionService.

Final ResponseEvaluation returned to user.

📌 Roadmap

 Implement real claim extraction in Python.

 Integrate evidence retrieval with vector DB.

 Add NLI models for fact-checking.

 Expand logging & observability.

 Explore IDE/DB plugins for integration.

🛠️ Tech Stack

Java 21, Spring Boot, Maven

Python 3.11, FastAPI, Uvicorn

Future: Hugging Face Transformers, Vector DB, PostgreSQL/Oracle
