CREATE TABLE IF NOT EXISTS pessoa (
    id serial PRIMARY KEY,
    nome VARCHAR(80) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(14) NOT NULL,
    endereco VARCHAR(100) NOT NULL
);  

INSERT INTO pessoa (nome, telefone, cpf, endereco) VALUES
('João Silva', '11 9 8765-4321', '123.456.789-01', 'Rua das Flores, 123 - São Paulo'),
('Maria Oliveira', '21 9 7654-3210', '234.567.890-12', 'Av. Brasil, 456 - Rio de Janeiro'),
('Carlos Souza', '31 9 6543-2109', '345.678.901-23', 'Rua Minas Gerais, 789 - Belo Horizonte'),
('Ana Pereira', '41 9 5432-1098', '456.789.012-34', 'Av. Paraná, 321 - Curitiba'),
('Fernanda Lima', '51 9 4321-0987', '567.890.123-45', 'Rua Porto Alegre, 654 - Porto Alegre');

SELECT * FROM pessoa;