# API de Cadastro de Pessoas e Posses com PostgreSQL

## 🚀 Visão Geral
API completa para cadastro e gerenciamento de pessoas e suas posses, desenvolvida com Spring Boot e integrada ao PostgreSQL via Hibernate. Oferece operações CRUD completas para ambas as entidades com tratamento avançado de exceções e validações.

## 🛠️ Tecnologias Utilizadas
- **Java 17**
- **Spring Boot 3.3.1**
  - Spring Data JPA
  - Spring Web MVC
  - Spring DevTools
- **Hibernate** (ORM)
- **PostgreSQL** (banco de dados)
- **Dozer Mapper** (para conversão entre entidades e DTOs)

## 📁 Estrutura do Projeto
```
src/
└── main/
    ├── java/br/com/david/
    │   ├── controller/                     # Camada de apresentação
    │   │   ├── PessoaController.java
    │   │   └── PosseController.java
    │   ├── exceptions/                     # Tratamento de erros
    │   │   ├── handler/
    │   │   │   └── ResponseExceptionHandler.java
    │   │   ├── ExceptionPessoaNaoEncontrada.java
    │   │   ├── ExceptionPosseNaoEncontrada.java
    │   │   ├── ExceptionResponse.java
    │   │   └── ResourceNotFoundException.java
    │   ├── mapper/                         # Mapeamento de objetos
    │   │   ├── DozerMapper.java
    │   │   └── custom/
    │   │       ├── PessoaDO.java
    │   │       └── PosseDO.java
    │   ├── model/                          # Entidades JPA
    │   │   ├── Pessoa.java
    │   │   └── Posse.java
    │   ├── repositories/                   # Camada de acesso a dados
    │   │   ├── PessoaRepository.java
    │   │   └── PosseRepository.java
    │   └── services/                       # Lógica de negócio
    │       ├── PessoaServices.java
    │       └── PosseService.java
    └── resources/
        ├── application.properties          # Configurações
        └── banco de dados.sql              # Scripts SQL iniciais
```

## 🔍 Componentes Principais

### 1. Entidades do Sistema

#### Pessoa
- **Campos**: 
  - id (auto-gerado)
  - nome (80 chars)
  - cpf (14 chars)
  - telefone (14 chars)
  - endereco (100 chars)
- **Validações**: Todos campos obrigatórios
- **Mapeamento**: `@Entity`, `@Table`, `@Column`

#### Posse
- **Campos**:
  - id (auto-gerado)
  - pessoa_id (relacionamento ManyToOne)
  - nome (100 chars)
  - descricao (255 chars)
  - valor (Decimal 12,2)
- **Relacionamento**: `@ManyToOne` com Pessoa
- **Validações**: Todos campos obrigatórios
- **Mapeamento**: `@Entity`, `@Table`, `@Column`

### 2. Value Objects (Padrão DTO)
O projeto utiliza o padrão Data Transfer Object (DTO) para transferência de dados entre camadas:

#### PessoaDO
- Representação simplificada de Pessoa
- Contém apenas id, nome e telefone
- Usado para evitar expor todos os campos da entidade

#### PosseDO
- Representação de Posse incluindo PessoaDO
- Permite controlar quais dados são expostos na API
- Evita problemas de serialização cíclica

### 3. Relacionamento entre Tabelas
- **Pessoa 1:N Posse**: Uma pessoa pode ter várias posses
- **Mapeamento JPA**: 
  - Na entidade Posse: `@ManyToOne @JoinColumn`
  - Configurado com `ON DELETE CASCADE` no banco
- **Consulta personalizada**: `findByPessoaId` no PosseRepository

### 4. Camadas Arquiteturais

#### Camada de Modelo
- `Pessoa.java` e `Posse.java`: Entidades JPA que representam as tabelas
- Anotações para mapeamento ORM e validações

#### Camada de Repositório
- `PessoaRepository` e `PosseRepository`: Interfaces que estendem `JpaRepository`
- Métodos padrão CRUD + consultas personalizadas

#### Camada de Serviço
- `PessoaServices` e `PosseService`: Implementam regras de negócio
- Tratamento de exceções específicas
- Validações antes de operações

#### Camada de Controller
- `PessoaController` e `PosseController`: Endpoints REST
- Consomem e produzem JSON
- Utilizam DTOs para transferência de dados
- Delegam operações para a camada de serviço

#### Camada de Mapeamento
- `DozerMapper`: Utiliza Dozer para conversão entre entidades e DTOs
- `PessoaDO` e `PosseDO`: Classes DTO para transferência controlada de dados

Aqui está a versão atualizada com os corpos de requisição e resposta para todos os endpoints:

## 🌐 Endpoints Detalhados

### Pessoas (`/pessoas`)

#### 1. Listar Todas as Pessoas
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
        "telefone": "11 9 8765-4321"
    },
    {
        "id": 2,
        "nome": "Maria Oliveira",
        "telefone": "21 9 7654-3210"
    }
]
```

---

#### 2. Buscar Pessoa por ID
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
    "telefone": "11 9 8765-4321"
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

---

#### 3. Criar Nova Pessoa
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

---

#### 4. Atualizar Pessoa
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

---

#### 5. Excluir Pessoa
**Requisição**:
```http
DELETE /pessoas/1
```

**Resposta de Sucesso** (204 No Content) - Sem corpo

---

### Posses (`/posses`)

#### 1. Listar Todas as Posses
**Requisição**:
```http
GET /posses
Accept: application/json
```

**Resposta de Sucesso** (200 OK):
```json
[
    {
        "id": 1,
        "pessoa": {
            "id": 1,
            "nome": "João Silva",
            "telefone": "11 9 8765-4321"
        },
        "nome": "Carro",
        "descricao": "Veículo Sedan 2020 - Preto",
        "valor": 75000.00
    },
    {
        "id": 2,
        "pessoa": {
            "id": 2,
            "nome": "Maria Oliveira",
            "telefone": "21 9 7654-3210"
        },
        "nome": "Apartamento",
        "descricao": "Apartamento 2 quartos no centro",
        "valor": 350000.00
    }
]
```

---

#### 2. Buscar Posse por ID
**Requisição**:
```http
GET /posses/1
Accept: application/json
```

**Resposta de Sucesso** (200 OK):
```json
{
    "id": 1,
    "pessoa": {
        "id": 1,
        "nome": "João Silva",
        "telefone": "11 9 8765-4321"
    },
    "nome": "Carro",
    "descricao": "Veículo Sedan 2020 - Preto",
    "valor": 75000.00
}
```

**Resposta de Erro** (404 Not Found):
```json
{
    "timestamp": "2024-09-19T12:00:00.000+00:00",
    "message": "Posse nao encontrada com o id: 99",
    "details": "uri=/posses/99"
}
```

---

#### 3. Buscar Posses por Pessoa
**Requisição**:
```http
GET /posses/pessoa/1
Accept: application/json
```

**Resposta de Sucesso** (200 OK):
```json
[
    {
        "id": 1,
        "pessoa": {
            "id": 1,
            "nome": "João Silva",
            "telefone": "11 9 8765-4321"
        },
        "nome": "Carro",
        "descricao": "Veículo Sedan 2020 - Preto",
        "valor": 75000.00
    }
]
```

---

#### 4. Criar Nova Posse
**Requisição**:
```http
POST /posses
Content-Type: application/json
Accept: application/json

{
    "pessoa": {
        "id": 1
    },
    "nome": "Notebook",
    "descricao": "Notebook i7 16GB RAM",
    "valor": 5000.00
}
```

**Resposta de Sucesso** (201 Created):
```json
{
    "id": 6,
    "pessoa": {
        "id": 1,
        "nome": "João Silva",
        "telefone": "11 9 8765-4321"
    },
    "nome": "Notebook",
    "descricao": "Notebook i7 16GB RAM",
    "valor": 5000.00
}
```

---

#### 5. Atualizar Posse
**Requisição**:
```http
PUT /posses
Content-Type: application/json
Accept: application/json

{
    "id": 1,
    "pessoa": {
        "id": 1
    },
    "nome": "Carro Atualizado",
    "descricao": "Veículo Sedan 2020 - Preto com rodas de liga",
    "valor": 80000.00
}
```

**Resposta de Sucesso** (200 OK):
```json
{
    "id": 1,
    "pessoa": {
        "id": 1,
        "nome": "João Silva",
        "telefone": "11 9 8765-4321"
    },
    "nome": "Carro Atualizado",
    "descricao": "Veículo Sedan 2020 - Preto com rodas de liga",
    "valor": 80000.00
}
```

---

#### 6. Excluir Posse
**Requisição**:
```http
DELETE /posses/1
```

**Resposta de Sucesso** (204 No Content) - Sem corpo

---

### Padrões de Resposta:
- **Sucesso**: Retorna os dados em JSON conforme cada endpoint
- **Erro**: Retorna estrutura padronizada:
  ```json
  {
    "timestamp": "2024-09-19T12:00:00.000+00:00",
    "message": "Mensagem de erro",
    "details": "uri=/endpoint"
  }
  ```

Observações:
1. Os DTOs (PessoaDO/PosseDO) controlam quais campos são expostos
2. Para criação/atualização, usa-se a entidade completa
3. Para consultas, retorna-se os DTOs com campos selecionados
4. Relacionamentos são representados de forma aninhada nos JSONs

## 💡 Observações Importantes

1. **Value Objects (DTOs)**:
   - Usados para controlar a exposição de dados na API
   - Evitam expor toda a estrutura das entidades
   - Previnem problemas como serialização cíclica

2. **Relacionamento Pessoa-Posse**:
   - Configurado como ManyToOne (muitas posses para uma pessoa)
   - No banco: chave estrangeira com ON DELETE CASCADE
   - Na API: endpoints específicos para buscar posses por pessoa

3. **Tratamento de Exceções**:
   - Exceções específicas para cada entidade
   - Respostas padronizadas em JSON
   - Status HTTP apropriados (404 para não encontrado, etc.)

4. **Mapeamento Objeto-Relacional**:
   - Hibernate converte automaticamente objetos Java para tabelas
   - Consultas JPQL implícitas nos métodos do repositório
   - Relacionamentos configurados via anotações JPA

## ▶️ Como Executar

### Pré-requisitos:
- PostgreSQL instalado e configurado
- Banco de dados criado
- Java 17 e Maven instalados

### Passos:
1. **Configure o banco** em `application.properties`
2. **Importe o projeto** como Maven Project na sua IDE
3. **Execute** `DemoApplication` (contém o método `main`)
4. **Teste os endpoints**:
   - Pessoas: `http://localhost:8080/pessoas`
   - Posses: `http://localhost:8080/posses`