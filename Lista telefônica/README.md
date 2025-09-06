# Sistema de Lista Telefônica

Sistema Java para gerenciamento de contatos telefônicos utilizando JDBC + Maven.

## Funcionalidades

- **Cadastrar contato**: Nome, Telefone, Email, Observação
- **Listar todos os contatos**: Visualização completa da agenda
- **Buscar contato por nome**: Pesquisa por nome ou parte do nome
- **Atualizar dados**: Modificar telefone, email e observação
- **Remover contato**: Exclusão de contatos da agenda

## Estrutura do Projeto

```
src/main/java/com/lista/
├── model/           # Entidade Contato
├── dao/             # ContatoDAO (CRUD)
├── util/            # ConnectionFactory
└── app/             # Main (interface do usuário)
```

## Pré-requisitos

- Java 11+
- Maven 3.6+
- MySQL 8.0+

## Configuração do Banco

1. Execute o script `schema.sql` no MySQL:
```sql
CREATE DATABASE lista_telefonica;
USE lista_telefonica;
-- Execute o conteúdo do schema.sql
```

2. Configure as credenciais em `ConnectionFactory.java` se necessário

## Execução

```bash
# Compilar o projeto
mvn clean compile

# Executar
mvn exec:java
```

## Menu de Opções

1. Cadastrar contato
2. Listar todos os contatos
3. Buscar contato por nome
4. Atualizar dados de um contato
5. Remover contato
6. Sair do sistema

## Tecnologias Utilizadas

- Java 11
- JDBC
- MySQL
- Maven
- POO (Programação Orientada a Objetos)
