FROM python:3.11-slim

# Instalar dependências do sistema para scipy e sentence-transformers
RUN apt-get update && apt-get install -y --no-install-recommends \
    libstdc++6 \
    && rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY app ./app
EXPOSE 8000
ENV PYTHONUNBUFFERED=1

# Criar usuário não-root
RUN useradd -m appuser
USER appuser

# Configurações otimizadas para uvicorn
CMD ["uvicorn", "app.main:app", "--host", "0.0.0.0", "--port", "8000", "--workers", "2"]