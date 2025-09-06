# Sistema de Logística de Entregas

Sistema Java completo para gestão de entregas de e-commerce utilizando JDBC + Maven.

## Funcionalidades

- **Cadastro de Clientes**: Nome, CPF/CNPJ, endereço, cidade, estado
- **Cadastro de Motoristas**: Nome, CNH, veículo, cidade base
- **Gestão de Pedidos**: Criação, cancelamento, status
- **Gestão de Entregas**: Atribuição a motoristas, histórico de eventos
- **Relatórios Analíticos**: 
  - Total de entregas por motorista
  - Clientes com maior volume entregue
  - Pedidos pendentes por estado
  - Entregas atrasadas por cidade

## Estrutura do Projeto

```
src/main/java/com/logistica/
├── config/          # Configuração do banco de dados
├── model/           # Entidades (Cliente, Motorista, Pedido, Entrega, etc.)
├── dao/             # Data Access Objects
├── service/         # Lógica de negócio
├── view/            # Interface do usuário
└── Main.java        # Classe principal
```

## Pré-requisitos

- Java 11+
- Maven 3.6+
- MySQL 8.0+

## Configuração do Banco

1. Execute o script `src/main/resources/schema.sql` no MySQL
2. Configure as credenciais em `DatabaseConfig.java` se necessário

## Execução

```bash
# Compilar o projeto
mvn clean compile

# Executar
mvn exec:java
```

## Menu de Opções

1. Cadastrar Cliente
2. Cadastrar Motorista
3. Criar Pedido
4. Atribuir Pedido a Motorista (Gerar Entrega)
5. Registrar Evento de Entrega (Histórico)
6. Atualizar Status da Entrega
7. Listar Todas as Entregas com Cliente e Motorista
8. Relatório: Total de Entregas por Motorista
9. Relatório: Clientes com Maior Volume Entregue
10. Relatório: Pedidos Pendentes por Estado
11. Relatório: Entregas Atrasadas por Cidade
12. Buscar Pedido por CPF/CNPJ do Cliente
13. Cancelar Pedido
14. Excluir Entrega (com validação)
15. Excluir Cliente (com verificação de dependência)
16. Excluir Motorista (com verificação de dependência)
0. Sair
