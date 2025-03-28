# 2024-08-14 Hello World Endpoint API

## 🚀 Visão Geral
Projeto Spring Boot mínimo que demonstra a criação de uma API REST básica com dois endpoints simples. Serve como ponto de partida para desenvolvimento de aplicações web com Spring.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.2**
  - Spring Web (para desenvolvimento de endpoints REST)
  - Spring DevTools (recarregamento automático em desenvolvimento)
  - Spring Test (infraestrutura básica para testes)

## 📁 Estrutura do Projeto
```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── DemoApplication.java       # Ponto de entrada da aplicação
│   │   └── PrimeiroController.java    # Lógica dos endpoints
│   └── resources/
│       └── application.properties     # Configuração básica
└── test/                             # Diretório para testes
    └── java/com/example/demo/
        └── DemoApplication.java       # Ponto de entrada da aplicação
```

## 🔍 Componentes Principais

### 1. DemoApplication
Classe principal que inicia a aplicação Spring Boot utilizando a anotação `@SpringBootApplication`. Responsável por carregar todas as configurações automáticas do Spring.

### 2. PrimeiroController
Controlador REST marcado com `@RestController` que contém dois endpoints:
- Endpoint raiz (`/`) que retorna a mensagem "Ola Mundo"
- Endpoint `/teste` que retorna a mensagem "testado"

### 3. application.properties
Arquivo de configuração básico que define apenas o nome da aplicação como "demo".

### 4. pom.xml
Arquivo de configuração Maven que define:
- Versão do Java (17)
- Dependências básicas do Spring Boot
- Plugin para execução da aplicação

## 🌐 Endpoints Disponíveis

| Rota       | Descrição               | Resposta Esperada |
|------------|-------------------------|-------------------|
| `GET /`    | Endpoint principal      | "Ola Mundo"       |
| `GET /teste` | Endpoint de teste     | "testado"         |

## ▶️ Como Executar

### Usando uma IDE:
1. **Importe o projeto** como *Maven Project* na sua IDE preferida (Eclipse, IntelliJ, VSCode)
2. **Execute** a classe `DemoApplication` (contém o método `main`)
3. **Acesse** os endpoints
