# 2024-08-17 API de Soma com Query Parameters

## 🚀 Visão Geral
API Spring Boot simples que demonstra o uso de parâmetros de consulta (query parameters) para realizar operações matemáticas. Oferece um endpoint para soma de dois números recebidos via URL.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.2**
  - Spring Web (para endpoints REST)
  - Spring DevTools (recarregamento automático)
  - Spring Test (infraestrutura de testes)

## 📁 Estrutura do Projeto
```
src/main/
    ├── java/com/example/demo/
    │   ├── DemoApplication.java       # Classe principal
    │   ├── Numero.java                # Modelo de resposta
    │   └── PrimeiroController.java    # Controlador com lógica
    └── resources/
        └── application.properties     # Configuração
```

## 🔍 Componentes Principais

### 1. DemoApplication
Classe de inicialização do Spring Boot que configura e inicia a aplicação.

### 2. PrimeiroController
Controlador REST que contém:
- Endpoint `/` para saudação básica
- Endpoint `/add` para soma de números com tratamento de parâmetros

### 3. Numero
Classe modelo que encapsula o resultado numérico para serialização JSON.

### 4. application.properties
Configuração básica com nome da aplicação.

## 🌐 Endpoints Disponíveis

| Método | Endpoint | Parâmetros | Descrição | Exemplo |
|--------|----------|------------|-----------|---------|
| GET | `/` | - | Retorna saudação | `http://localhost:8080/` |
| GET | `/add` | `a` e `b` (números) | Soma dois números | `http://localhost:8080/add?a=5&b=3` |

## ▶️ Como Executar

### Usando uma IDE:
1. **Importe o projeto** como Maven Project
2. **Execute** a classe `DemoApplication`
3. **Teste os endpoints**:
   - Acesso básico: `http://localhost:8080/`
   - Soma: `http://localhost:8080/add?a=5&b=3`
