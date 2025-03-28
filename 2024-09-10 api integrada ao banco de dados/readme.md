# 2024-09-19 API de Cadastro de Pessoas com PostgreSQL

## 🚀 Visão Geral
API completa para cadastro e gerenciamento de pessoas, desenvolvida com Spring Boot e integrada ao PostgreSQL via Hibernate. Oferece operações CRUD completas com tratamento avançado de exceções e validações.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.1**
  - Spring Data JPA
  - Spring Web MVC
  - Spring DevTools
- **Hibernate** (ORM)
- **PostgreSQL** (banco de dados)

## 📁 Estrutura do Projeto
```
src/
└── main/
    ├── java/br/com/david/
    │   ├── controller/                     # Camada de apresentação
    │   │   └── PessoaController.java
    │   ├── exceptions/                     # Tratamento de erros
    │   │   ├── handler/
    │   │   │   └── CustomizedResponseEntityExceptionHandler.java
    │   │   ├── ExceptionPessoaNaoEncontrada.java
    │   │   ├── ExceptionResponse.java
    │   │   └── ResourceNotFoundException.java
    │   ├── model/                          # Entidades JPA
    │   │   └── Pessoa.java
    │   ├── repositories/                   # Camada de acesso a dados
    │   │   └── PessoaRepository.java
    │   └── services/                       # Lógica de negócio
    │       └── PessoaServices.java
    └── resources/
        ├── application.properties          # Configurações
        └── banco de dados.sql
```

## 🔍 Componentes Principais

### 1. Entidade Pessoa
- **Campos**: id (auto-gerado), nome (80 chars), cpf (14 chars), telefone (14 chars), endereco (100 chars)
- **Validações**: Todos campos obrigatórios
- **Mapeamento**: `@Entity`, `@Table`, `@Column`

### 2. Camadas Arquiteturais Do Sistema
- **Modelo (Pessoa.java)** -Entidade JPA que representa a tabela no banco de dados:
    - Mapeamento com `@Entity` e `@Table`
    - Campos obrigatórios (`@Column(nullable = false)`)
    - Geração automática de ID (`@GeneratedValue`)
    - Serialização para JSON automática

- **PessoaRepositoryInterface** - Camada que estende `JpaRepository` herdando operações CRUD básicas:
    - `findAll()`, `findById()`, `save()`, `delete()`, `update()`
    - Implementação automática pelo Spring Data JPA

- **PessoaServices** - Camada de serviço que:
    - Implementa regras de negócio
    - Trata exceções específicas
    - Utiliza o repositório para persistência
    - Valida existência de entidades antes de operações

- **PessoaController** - Expõe endpoints REST com:
    - Mapeamento preciso de verbos HTTP
    - Tipos de mídia definidos (JSON)
    - Delegação para camada de serviço
    - Tratamento de respostas HTTP apropriadas

- **Tratamento de Exceções** - Sistema completo com:
    - `@ControllerAdvice` para tratamento global
    - `ExceptionResponse` padrão para erros
    - HTTP Status codes específicos:
    - 404 (NOT FOUND) para recursos inexistentes
    - 500 (INTERNAL SERVER ERROR) para erros genéricos


## 💡 Funcionamento Interno

1. **Requisição chega** no Controller
2. **Controller delega** para a camada Service
3. **Service valida** e utiliza o Repository
4. **Repository traduz** operações para SQL via Hibernate
5. **Resposta retorna** através das camadas

O Spring Data JPA + Hibernate automaticamente:
- Traduz objetos Java para tabelas SQL
- Converte resultados SQL para objetos Java
- Gerencia transações e conexões
- Otimiza consultas ao banco

## 🌐 Endpoints Detalhados

### 1. Listar Todas as Pessoas
**Requisição**:
```http
GET /pessoas
Accept: application/json
```

**Resposta de Sucesso** (200 OK):
```json
[
    {
        "id": 1,
        "nome": "João Silva",
        "cpf": "123.456.789-00",
        "telefone": "(11) 9999-8888",
        "endereco": "Rua A, 123"
    },
    {
        "id": 2,
        "nome": "Maria Souza",
        "cpf": "987.654.321-00",
        "telefone": "(21) 7777-6666",
        "endereco": "Av. B, 456"
    }
]
```

### 2. Buscar Pessoa por ID
**Requisição**:
```http
GET /pessoas/1
Accept: application/json
```

**Resposta de Sucesso** (200 OK):
```json
{
    "id": 1,
    "nome": "João Silva",
    "cpf": "123.456.789-00",
    "telefone": "(11) 9999-8888",
    "endereco": "Rua A, 123"
}
```

**Resposta de Erro** (404 Not Found):
```json
{
    "timestamp": "2024-09-19T12:00:00.000+00:00",
    "message": "Pessoa nao encontrada com o id: 99",
    "details": "uri=/pessoas/99"
}
```

### 3. Criar Nova Pessoa
**Requisição**:
```http
POST /pessoas
Content-Type: application/json
Accept: application/json

{
    "nome": "Carlos Oliveira",
    "cpf": "456.123.789-00",
    "telefone": "(31) 8888-7777",
    "endereco": "Rua C, 789"
}
```

**Resposta de Sucesso** (201 Created):
```json
{
    "id": 3,
    "nome": "Carlos Oliveira",
    "cpf": "456.123.789-00",
    "telefone": "(31) 8888-7777",
    "endereco": "Rua C, 789"
}
```

### 4. Atualizar Pessoa
**Requisição**:
```http
PUT /pessoas
Content-Type: application/json
Accept: application/json

{
    "id": 1,
    "nome": "João Silva Atualizado",
    "cpf": "123.456.789-00",
    "telefone": "(11) 9999-0000",
    "endereco": "Rua A, 123 - apto 101"
}
```

**Resposta de Sucesso** (200 OK):
```json
{
    "id": 1,
    "nome": "João Silva Atualizado",
    "cpf": "123.456.789-00",
    "telefone": "(11) 9999-0000",
    "endereco": "Rua A, 123 - apto 101"
}
```

### 5. Excluir Pessoa
**Requisição**:
```http
DELETE /pessoas/1
```

**Resposta de Sucesso** (204 No Content) - Sem corpo

## 💡 Observações Importantes
1. Todos os campos são obrigatórios
2. O ID é auto-gerado nas operações de criação
3. Formato de CPF e telefone devem respeitar tamanhos máximos
4. Erros retornam formato padronizado com timestamp, mensagem e detalhes

### Padrões de Resposta:
- **Sucesso**: Retorna os dados em JSON
- **Erro**: Retorna estrutura padronizada:
  ```json
  {
    "timestamp": "2024-09-19T12:00:00.000+00:00",
    "message": "Mensagem de erro",
    "details": "uri=/pessoas/99"
  }
  ```

## ▶️ Como Executar:

### Pré-requisitos:
- PostgreSQL instalado e configurado
- Banco de dados criado
- Java 17 e Maven instalados

### Passos:
1. **Configure o banco** em `application.properties`
2. **Importe o projeto** como Maven Project na sua IDE
3. **Execute** `DemoApplication` (contém o método `main`)
4. **Teste os endpoints** acessando `http://localhost:8080/pessoas`
