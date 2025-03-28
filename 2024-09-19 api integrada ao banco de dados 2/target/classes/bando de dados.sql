CREATE TABLE IF NOT EXISTS pessoa (
    id serial PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    endereco VARCHAR(100) NOT NULL
);  

CREATE TABLE IF NOT EXISTS posse (
    id SERIAL PRIMARY KEY,
    pessoa_id INT NOT NULL,
    nome VARCHAR(80) NOT NULL, 
    descricao VARCHAR(255) NOT NULL,
    valor DECIMAL(12,2) NOT NULL, 
    FOREIGN KEY (pessoa_id) REFERENCES pessoa(id) ON DELETE CASCADE
);

INSERT INTO pessoa (nome, telefone, cpf, endereco) VALUES
('João Silva', '11 9 8765-4321', '123.456.789-01', 'Rua das Flores, 123 - São Paulo'),
('Maria Oliveira', '21 9 7654-3210', '234.567.890-12', 'Av. Brasil, 456 - Rio de Janeiro'),
('Carlos Souza', '31 9 6543-2109', '345.678.901-23', 'Rua Minas Gerais, 789 - Belo Horizonte'),
('Ana Pereira', '41 9 5432-1098', '456.789.012-34', 'Av. Paraná, 321 - Curitiba'),
('Fernanda Lima', '51 9 4321-0987', '567.890.123-45', 'Rua Porto Alegre, 654 - Porto Alegre');

INSERT INTO posse (pessoa_id, nome, descricao, valor) VALUES
(1, 'Carro', 'Veículo Sedan 2020 - Preto', 75000.00),
(2, 'Apartamento', 'Apartamento 2 quartos no centro', 350000.00),
(3, 'Moto', 'Moto esportiva 2022 - Vermelha', 45000.00),
(4, 'Terreno', 'Terreno 500m² na zona rural', 120000.00),
(5, 'Loja', 'Loja comercial no shopping', 500000.00);

SELECT 
    posse.id AS posse_id,
    posse.nome AS posse_nome,
    posse.descricao,
    posse.valor,
    pessoa.id AS pessoa_id,
    pessoa.nome AS pessoa_nome,
    pessoa.cpf,
    pessoa.telefone,
    pessoa.endereco
FROM posse
JOIN pessoa ON posse.pessoa_id = pessoa.id;
