# 2024-08-27 API Calculadora com Path Parameters e Tratamento de Exceções

## 🚀 Visão Geral
API Spring Boot que implementa uma calculadora básica recebendo operações matemáticas via path parameters. Destaque para o tratamento robusto de exceções e validação de parâmetros.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.1**
  - Spring Web (endpoints REST)
  - Spring DevTools (desenvolvimento)
  - Spring Test (testes)
- **Tratamento de Exceções**:
  - `@ControllerAdvice` para handlers globais
  - `@ResponseStatus` para códigos HTTP personalizados

## 📁 Estrutura do Projeto
```
src/
└── main/
    ├── java/br/com/david/
    │   ├── DemoApplication.java                 # Classe principal
    │   ├── MathController.java                  # Controlador
    │   ├── utils/
    │   │   ├── Calc.java                       # Lógica matemática
    │   │   └── Parse.java                      # Validação de números
    │   └── exceptions/
    │       ├── handler/                        # Tratamento global
    │       │   └── CustomizedResponseEntityExceptionHandler.java
    │       ├── ExceptionResponse.java          # Modelo de erro
    │       └── UnsupportedMathOperationException.java
    └── resources/
        └── application.properties              # Configuração
```

## 🔍 Componentes Principais

### 1. MathController
Controlador REST que expõe o endpoint `/calc/{val1}/{op}/{val2}` para operações matemáticas, utilizando path parameters.

### 2. Utils (Calc e Parse)
- **Calc**: Implementa as operações matemáticas básicas (+, -, *, /)
- **Parse**: Valida e converte strings para números com tratamento de formatos

### 3. Tratamento de Exceções
Sistema completo com:
- `UnsupportedMathOperationException`: Exceção customizada
- `ExceptionResponse`: Modelo padronizado para respostas de erro
- `CustomizedResponseEntityExceptionHandler`: Centralizador de tratamentos

### 4. application.properties
Configuração básica com nome da aplicação.

## 🌐 Endpoint Disponível

| Método | Endpoint | Parâmetros | Descrição | Exemplo Válido | Exemplo Inválido |
|--------|----------|------------|-----------|----------------|------------------|
| GET | `/calc/{val1}/{op}/{val2}` | val1: número<br>op: operador (+,-,*,/)<br>val2: número | Executa operação matemática | `/calc/5/+/3` | `/calc/a/+/b` |

## ▶️ Como Executar

### Usando uma IDE:
1. **Importe o projeto** como Maven Project
2. **Execute** a classe `DemoApplication`
3. **Teste os endpoints**:
   - Operação válida: `http://localhost:8080/calc/5/+/3`
   - Operação inválida: `http://localhost:8080/calc/a/+/b`
