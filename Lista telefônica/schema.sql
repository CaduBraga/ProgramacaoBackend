CREATE DATABASE IF NOT EXISTS lista_telefonica;
USE lista_telefonica;

CREATE TABLE IF NOT EXISTS contato (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    email VARCHAR(100),
    observacao TEXT
);

INSERT INTO contato (nome, telefone, email, observacao) VALUES
('João Silva', '(11) 99999-9999', 'joao@email.com', 'Amigo da faculdade'),
('Maria Santos', '(11) 88888-8888', 'maria@email.com', 'Colega de trabalho'),
('Pedro Oliveira', '(11) 77777-7777', 'pedro@email.com', 'Vizinho'),
('Ana Costa', '(11) 66666-6666', 'ana@email.com', 'Prima'),
('Carlos Ferreira', '(11) 55555-5555', 'carlos@email.com', 'Contato comercial');
