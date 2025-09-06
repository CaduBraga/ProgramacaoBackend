CREATE DATABASE IF NOT EXISTS crud_jdbc;
USE crud_jdbc;

CREATE TABLE IF NOT EXISTS alunos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    idade INT NOT NULL,
    curso VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS funcionarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cargo VARCHAR(50) NOT NULL,
    salario DECIMAL(10,2) NOT NULL,
    departamento VARCHAR(50) NOT NULL,
    data_admissao DATE NOT NULL
);

CREATE TABLE IF NOT EXISTS livros (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    autor VARCHAR(100) NOT NULL,
    isbn VARCHAR(20) UNIQUE,
    preco DECIMAL(8,2) NOT NULL,
    estoque INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS pedidos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cliente_nome VARCHAR(100) NOT NULL,
    produto VARCHAR(100) NOT NULL,
    quantidade INT NOT NULL,
    valor_total DECIMAL(10,2) NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS produtos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao TEXT,
    preco DECIMAL(8,2) NOT NULL,
    categoria VARCHAR(50) NOT NULL,
    estoque INT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    ativo BOOLEAN DEFAULT TRUE,
    data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

INSERT INTO alunos (nome, idade, curso, email) VALUES
('João Silva', 20, 'Ciência da Computação', 'joao@email.com'),
('Maria Santos', 22, 'Engenharia', 'maria@email.com'),
('Pedro Oliveira', 19, 'Administração', 'pedro@email.com'),
('Ana Costa', 21, 'Medicina', 'ana@email.com'),
('Carlos Pereira', 23, 'Direito', 'carlos@email.com');

INSERT INTO funcionarios (nome, cargo, salario, departamento, data_admissao) VALUES
('Roberto Silva', 'Gerente', 5000.00, 'Vendas', '2020-01-15'),
('Fernanda Lima', 'Desenvolvedora', 3500.00, 'TI', '2021-03-10'),
('Antonio Santos', 'Analista', 2800.00, 'Financeiro', '2019-06-20'),
('Juliana Costa', 'Designer', 3200.00, 'Marketing', '2022-02-01'),
('Marcos Oliveira', 'Assistente', 2000.00, 'RH', '2023-01-05');

INSERT INTO livros (titulo, autor, isbn, preco, estoque) VALUES
('Java: Como Programar', 'Deitel', '978-0134448237', 150.00, 10),
('Clean Code', 'Robert Martin', '978-0132350884', 89.90, 5),
('Design Patterns', 'Gang of Four', '978-0201633610', 120.00, 8),
('Spring in Action', 'Craig Walls', '978-1617294945', 95.50, 12),
('Effective Java', 'Joshua Bloch', '978-0134685991', 110.00, 6);

INSERT INTO pedidos (cliente_nome, produto, quantidade, valor_total) VALUES
('João Silva', 'Notebook Dell', 1, 2500.00),
('Maria Santos', 'Mouse Logitech', 2, 50.00),
('Pedro Oliveira', 'Teclado Mecânico', 1, 200.00),
('Ana Costa', 'Monitor Samsung', 1, 800.00),
('Carlos Pereira', 'Headset Gamer', 1, 150.00);

INSERT INTO produtos (nome, descricao, preco, categoria, estoque) VALUES
('Notebook Dell Inspiron', 'Notebook 15 polegadas, 8GB RAM', 2500.00, 'Informática', 5),
('Mouse Logitech M100', 'Mouse óptico USB', 25.00, 'Periféricos', 20),
('Teclado Mecânico', 'Teclado mecânico RGB', 200.00, 'Periféricos', 8),
('Monitor Samsung 24"', 'Monitor Full HD 24 polegadas', 800.00, 'Monitores', 3),
('Headset Gamer', 'Headset com microfone', 150.00, 'Áudio', 15);

INSERT INTO usuarios (username, email, senha) VALUES
('admin', 'admin@email.com', 'admin123'),
('user1', 'user1@email.com', 'user123'),
('user2', 'user2@email.com', 'user456'),
('teste', 'teste@email.com', 'teste123'),
('demo', 'demo@email.com', 'demo123');
