AI Hallucination Detector

AI Hallucination Detector is an experimental project combining Java (Spring Boot) and Python (FastAPI).
It provides a hybrid architecture where:

Java Backend → Orchestrates requests, exposes the main REST API, integrates with databases and decision logic.

Python Microservice → Processes NLP-related checks (currently mocked, with plans for claim extraction, embeddings, and fact-checking).

This modular design makes it scalable and ready to evolve into a complete fact-checking and response reliability system.

📂 Project Structure
hallucination-detector/
│
├── java-backend/           # Spring Boot application
│   └── src/main/java/com/triminds/factcheck/
│       ├── controller/
│       ├── service/
│       ├── model/
│       ├── repository/
│       └── config/
│
├── python-ml/              # FastAPI microservice
│   ├── app/
│   │   ├── main.py
│   │   ├── routers/
│   │   ├── services/
│   │   ├── models/
│   │   └── config/
│   ├── requirements.txt
│   └── Dockerfile
│
└── README.md

🚀 Getting Started
Java Backend
cd java-backend
mvn spring-boot:run

Python Microservice
cd python-ml
pip install -r requirements.txt
uvicorn app.main:app --reload --port 8001

🔄 Workflow

User sends text to Java Backend (/verify)

Java calls the Python microservice /nlp/verify

Python returns claims & evidence (mocked for now)

Java evaluates response reliability

Result returned to user

📌 Roadmap

 Implement claim extraction (Python)

 Add evidence retrieval with vector DB

 Integrate NLI models for fact-checking

 Expand observability & logging

 IDE/DB plugins for practical integration

🛠️ Tech Stack

Java 21, Spring Boot, Maven

Python 3.11, FastAPI, Uvicorn

Future: Hugging Face Transformers, Vector DB, PostgreSQL/Oracle
