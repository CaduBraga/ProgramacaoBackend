-- Script SQL para criação do banco de dados e tabelas
-- Sistema de Logística de Entregas

-- Criar banco de dados (se não existir)
CREATE DATABASE IF NOT EXISTS logistica_entregas;
USE logistica_entregas;

-- Tabela de Clientes
CREATE TABLE IF NOT EXISTS clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cpf_cnpj VARCHAR(20) NOT NULL UNIQUE,
    endereco VARCHAR(500) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado VARCHAR(2) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Motoristas
CREATE TABLE IF NOT EXISTS motoristas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    cnh VARCHAR(20) NOT NULL UNIQUE,
    veiculo VARCHAR(100) NOT NULL,
    cidade_base VARCHAR(100) NOT NULL,
    data_cadastro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Tabela de Pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    data_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    volume_m3 DECIMAL(10,3) NOT NULL,
    peso_kg DECIMAL(10,3) NOT NULL,
    status ENUM('PENDENTE', 'ENTREGUE', 'CANCELADO') DEFAULT 'PENDENTE',
    FOREIGN KEY (cliente_id) REFERENCES clientes(id) ON DELETE CASCADE
);

-- Tabela de Entregas
CREATE TABLE IF NOT EXISTS entregas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    motorista_id BIGINT NOT NULL,
    data_saida TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    data_entrega TIMESTAMP NULL,
    status ENUM('EM_ROTA', 'ENTREGUE', 'ATRASADA') DEFAULT 'EM_ROTA',
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id) ON DELETE CASCADE,
    FOREIGN KEY (motorista_id) REFERENCES motoristas(id) ON DELETE CASCADE
);

-- Tabela de Histórico de Entregas
CREATE TABLE IF NOT EXISTS historico_entregas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    entrega_id BIGINT NOT NULL,
    data_evento TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descricao TEXT NOT NULL,
    FOREIGN KEY (entrega_id) REFERENCES entregas(id) ON DELETE CASCADE
);

-- Índices para melhorar performance
CREATE INDEX idx_clientes_cpf_cnpj ON clientes(cpf_cnpj);
CREATE INDEX idx_clientes_estado ON clientes(estado);
CREATE INDEX idx_motoristas_cnh ON motoristas(cnh);
CREATE INDEX idx_motoristas_cidade ON motoristas(cidade_base);
CREATE INDEX idx_pedidos_cliente ON pedidos(cliente_id);
CREATE INDEX idx_pedidos_status ON pedidos(status);
CREATE INDEX idx_entregas_pedido ON entregas(pedido_id);
CREATE INDEX idx_entregas_motorista ON entregas(motorista_id);
CREATE INDEX idx_entregas_status ON entregas(status);
CREATE INDEX idx_historico_entrega ON historico_entregas(entrega_id);

-- Inserir dados de exemplo
INSERT INTO clientes (nome, cpf_cnpj, endereco, cidade, estado) VALUES
('João Silva', '123.456.789-00', 'Rua das Flores, 123', 'São Paulo', 'SP'),
('Maria Santos', '987.654.321-00', 'Av. Paulista, 456', 'São Paulo', 'SP'),
('Empresa ABC Ltda', '12.345.678/0001-90', 'Rua Comercial, 789', 'Rio de Janeiro', 'RJ'),
('Pedro Oliveira', '111.222.333-44', 'Rua da Paz, 321', 'Belo Horizonte', 'MG'),
('Ana Costa', '555.666.777-88', 'Av. Central, 654', 'Salvador', 'BA');

INSERT INTO motoristas (nome, cnh, veiculo, cidade_base) VALUES
('Carlos Motorista', '12345678901', 'Van Mercedes Sprinter', 'São Paulo'),
('Roberto Entregador', '98765432109', 'Caminhão Ford Cargo', 'Rio de Janeiro'),
('Antonio Logística', '11122233344', 'Van Volkswagen Delivery', 'Belo Horizonte'),
('Francisco Transporte', '55566677788', 'Caminhão Scania', 'Salvador'),
('José Entrega', '99988877766', 'Van Iveco Daily', 'São Paulo');

INSERT INTO pedidos (cliente_id, volume_m3, peso_kg) VALUES
(1, 0.5, 15.5),
(2, 1.2, 25.0),
(3, 2.0, 50.0),
(4, 0.8, 18.0),
(5, 1.5, 30.0);

INSERT INTO entregas (pedido_id, motorista_id) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 3),
(5, 4);

INSERT INTO historico_entregas (entrega_id, descricao) VALUES
(1, 'Entrega iniciada - saída do depósito'),
(1, 'Em trânsito para o destino'),
(2, 'Entrega iniciada - saída do depósito'),
(3, 'Entrega iniciada - saída do depósito'),
(4, 'Entrega iniciada - saída do depósito'),
(5, 'Entrega iniciada - saída do depósito');
