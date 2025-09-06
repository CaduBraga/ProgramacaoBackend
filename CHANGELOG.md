# Changelog

Todas as mudanças notáveis neste projeto serão documentadas neste arquivo.

O formato é baseado em [Keep a Changelog](https://keepachangelog.com/pt-BR/1.0.0/),
e este projeto adere ao [Versionamento Semântico](https://semver.org/lang/pt-BR/).

## [1.0.0] - 2024-12-19

### Adicionado
- **Sistema CRUD-JDBC Completo**: Implementação de operações CRUD usando JDBC
  - Conexão com banco de dados MySQL
  - Operações Create, Read, Update, Delete para 6 tabelas
  - Interface de usuário via console
  - Padrão MVC implementado

- **Estrutura MVC**:
  - **Model**: Classes DAO (Data Access Object) para acesso aos dados
  - **View**: Interface de usuário via console (`MainView.java`)
  - **Controller/Service**: Classes de serviço que coordenam as operações

- **Tabelas do Banco de Dados**:
  - `usuarios` - Gerenciamento de usuários
  - `alunos` - Cadastro de estudantes
  - `produtos` - Controle de produtos
  - `pedidos` - Gestão de pedidos
  - `livros` - Biblioteca de livros
  - `funcionarios` - Cadastro de funcionários

- **Classes DAO Implementadas**:
  - `AlunoDAO.java` - Operações CRUD para alunos
  - `FuncionariosDAO.java` - Operações CRUD para funcionários
  - `LivrosDAO.java` - Operações CRUD para livros
  - `PedidosDAO.java` - Operações CRUD para pedidos
  - `ProdutosDAO.java` - Operações CRUD para produtos
  - `UsuarioDAO.java` - Operações CRUD para usuários

- **Classes de Serviço**:
  - `Gerenciamento.java` - Controlador principal
  - `Create.java` - Operações de criação
  - `Read.java` - Operações de leitura
  - `Update.java` - Operações de atualização
  - `Delete.java` - Operações de exclusão

- **Configuração de Banco**:
  - `Conexao.java` - Configuração de conexão com MySQL
  - Script SQL em `resources/db/database.sql`
  - Suporte ao MySQL Connector/J 8.0.33

### Características
- Implementação completa de JDBC com Java
- Padrão MVC (Model-View-Controller)
- Operações CRUD completas
- Interface de usuário interativa
- Tratamento de exceções robusto
- Uso de PreparedStatement para segurança
- Código modular e bem documentado

### Tecnologias
- Java 22
- Maven 3.6+
- MySQL Server
- MySQL Connector/J 8.0.33
- JDBC API

## [0.9.0] - 2024-12-18

### Adicionado
- Estrutura inicial do projeto CRUD-JDBC
- Configuração do Maven
- Primeiras classes de conexão com banco

### Corrigido
- Configurações do projeto
- Estrutura de diretórios
- Arquivos de configuração

## [0.8.0] - 2024-12-17

### Adicionado
- Classes DAO básicas
- Estrutura MVC inicial
- Configuração do banco de dados

## [0.7.0] - 2024-12-16

### Adicionado
- Classes de serviço (Create, Read, Update, Delete)
- Interface de usuário básica
- Scripts de banco de dados

## [0.6.0] - 2024-12-15

### Adicionado
- Configuração de conexão com MySQL
- Estrutura de pacotes organizada
- Documentação inicial

## [0.5.0] - 2024-12-14

### Adicionado
- Estrutura básica do projeto
- Configuração do ambiente de desenvolvimento
- Primeiros testes de conexão

## [0.4.0] - 2024-12-13

### Adicionado
- Criação do repositório
- Configuração inicial do Git
- Estrutura básica de diretórios

## [0.3.0] - 2024-12-12

### Adicionado
- Planejamento da arquitetura MVC
- Definição das tabelas do banco de dados
- Estrutura de classes planejada

## [0.2.0] - 2024-12-11

### Adicionado
- Estrutura inicial do projeto
- Configuração do ambiente de desenvolvimento
- Primeiros exercícios práticos

## [0.1.0] - 2024-12-10

### Adicionado
- Criação do repositório
- Configuração inicial do Git
- Estrutura básica de diretórios

---

## Tipos de Mudanças

- **Adicionado** para novas funcionalidades
- **Alterado** para mudanças em funcionalidades existentes
- **Descontinuado** para funcionalidades que serão removidas em breve
- **Removido** para funcionalidades removidas
- **Corrigido** para correções de bugs
- **Segurança** para vulnerabilidades corrigidas

---

**Desenvolvido por** CaduBraga  
**Turma MIDS 77** - Curso de Desenvolvimento de Sistemas  
**Professor**: Vinícius Trindade