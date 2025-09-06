# Programação Backend - Projetos Java + JDBC

Repositório contendo projetos práticos de programação backend em Java utilizando JDBC, Maven e MySQL.

## Projetos Implementados

### 1. Sistema de Logística de Entregas 🚚
Sistema completo para gestão de entregas de e-commerce com funcionalidades avançadas de relatórios e análise.

**Funcionalidades:**
- Cadastro de clientes, motoristas, pedidos e entregas
- Gestão de histórico de entregas
- 16 funcionalidades completas incluindo relatórios analíticos
- Validações de dependência para exclusões
- Interface de usuário interativa

### 2. Sistema de Lista Telefônica 📞
Sistema simples para gerenciamento de contatos telefônicos seguindo padrões de POO.

**Funcionalidades:**
- CRUD completo de contatos
- Busca por nome
- Interface de usuário via console
- Estrutura em camadas (model, dao, util, app)

### 3. CRUD-JDBC com PreparedStatement 💾
Sistema CRUD completo com exercícios práticos de JDBC.

**Funcionalidades:**
- Operações CRUD para 6 tabelas diferentes
- Exercícios práticos com PreparedStatement
- Padrão MVC implementado
- Interface de usuário interativa

## Estrutura dos Projetos

### Sistema de Logística de Entregas
```
Sistema de logística e entregas/
├── src/main/java/com/logistica/
│   ├── config/          # DatabaseConfig
│   ├── model/           # Entidades (Cliente, Motorista, Pedido, etc.)
│   ├── dao/             # Data Access Objects
│   ├── service/         # Lógica de negócio
│   ├── view/            # Interface do usuário
│   └── Main.java        # Classe principal
├── src/main/resources/schema.sql
└── pom.xml
```

### Sistema de Lista Telefônica
```
Lista telefônica/
├── src/main/java/com/lista/
│   ├── model/           # Contato
│   ├── dao/             # ContatoDAO
│   ├── util/            # ConnectionFactory
│   └── app/             # Main
├── schema.sql
└── pom.xml
```

### CRUD-JDBC com PreparedStatement
```
CRUD - JDBC/
├── src/main/java/org/example/
│   ├── config/          # Conexao
│   ├── main/            # Main e ExerciciosPraticos
│   ├── service/         # Operações CRUD
│   └── view/            # Interface do usuário
└── schema.sql
```

## 🚀 Início Rápido

### Execução Automatizada
Use os scripts fornecidos para facilitar a execução:

**Windows:**
```cmd
run.bat
```

**Linux/macOS:**
```bash
chmod +x run.sh
./run.sh
```

### Execução Manual

#### Sistema de Logística de Entregas
```bash
cd "Sistema de logística e entregas"
mvn clean compile
mvn exec:java
```

#### Sistema de Lista Telefônica
```bash
cd "Lista telefônica"
mvn clean compile
mvn exec:java
```

#### CRUD-JDBC com PreparedStatement
```bash
cd "CRUD - JDBC"
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.main.Main"
```

### Documentação Detalhada
- 📋 **[INDEX.md](INDEX.md)** - Índice completo de projetos
- 🛠️ **[INSTALL.md](INSTALL.md)** - Guia de instalação e configuração
- 📝 **[CHANGELOG.md](CHANGELOG.md)** - Histórico de mudanças

## Configuração do Banco de Dados

### MySQL Setup
1. Instale o MySQL Server 8.0+
2. Execute os scripts SQL fornecidos em cada projeto
3. Configure as credenciais de conexão nos arquivos de configuração

### Scripts SQL
- **Logística**: `Sistema de logística e entregas/src/main/resources/schema.sql`
- **Lista Telefônica**: `Lista telefônica/schema.sql`
- **CRUD-JDBC**: `CRUD - JDBC/schema.sql`

## Exemplos de Uso

### Sistema de Logística - Cadastrar Cliente
```java
ClienteService clienteService = new ClienteService();
Cliente cliente = new Cliente("João Silva", "123.456.789-00", 
    "Rua das Flores, 123", "São Paulo", "SP");
Long id = clienteService.cadastrarCliente(cliente);
```

### Sistema de Lista Telefônica - Buscar Contato
```java
ContatoDAO contatoDAO = new ContatoDAO();
List<Contato> contatos = contatoDAO.buscarTodosPorNome("João");
```

### CRUD-JDBC - Atualizar Usuário
```java
UsuarioDAO usuarioDAO = new UsuarioDAO();
boolean sucesso = usuarioDAO.atualizarEmail("Ana Souza", "ana.nova@email.com");
```

## Características dos Projetos

### Sistema de Logística de Entregas
- **Arquitetura em Camadas**: Model, DAO, Service, View
- **Relatórios Avançados**: JOINs, GROUP BY, filtros complexos
- **Validações de Negócio**: Dependências entre entidades
- **Interface Completa**: 16 funcionalidades implementadas
- **Tratamento de Erros**: Validações robustas e mensagens informativas

### Sistema de Lista Telefônica
- **Padrão POO**: Encapsulamento, herança, polimorfismo
- **CRUD Completo**: Create, Read, Update, Delete
- **Estrutura Simples**: Fácil de entender e modificar
- **Interface Console**: Menu interativo intuitivo

### CRUD-JDBC com PreparedStatement
- **PreparedStatement**: Segurança contra SQL Injection
- **Padrão MVC**: Separação clara de responsabilidades
- **Exercícios Práticos**: 15 exercícios de UPDATE e DELETE
- **Múltiplas Tabelas**: 6 entidades diferentes para prática

## Tecnologias Utilizadas

- **Java 11+**
- **Maven 3.6+**
- **MySQL 8.0+**
- **JDBC API**
- **MySQL Connector/J 8.0.33**

## Estrutura de Arquivos

```
ProgramacaoBackend/
├── Sistema de logística e entregas/     # Sistema completo de logística
├── Lista telefônica/                    # Sistema de lista telefônica
├── CRUD - JDBC/                        # Exercícios práticos de JDBC
├── README.md                           # Este arquivo
├── INDEX.md                            # Índice de projetos
├── INSTALL.md                          # Guia de instalação
├── CHANGELOG.md                        # Histórico de mudanças
├── CONTRIBUTING.md                     # Diretrizes de contribuição
├── CODE_OF_CONDUCT.md                  # Código de conduta
├── LICENSE                             # Licença do projeto
├── pom.xml                             # Configuração Maven principal
├── run.bat                             # Script de execução (Windows)
└── run.sh                              # Script de execução (Linux/macOS)
```

## Funcionalidades por Projeto

### 🚚 Sistema de Logística de Entregas
- ✅ Cadastro de Clientes, Motoristas, Pedidos
- ✅ Gestão de Entregas e Histórico
- ✅ 16 Relatórios Analíticos
- ✅ Validações de Dependência
- ✅ Interface Completa

### 📞 Sistema de Lista Telefônica
- ✅ CRUD de Contatos
- ✅ Busca por Nome
- ✅ Interface Console
- ✅ Estrutura POO

### 💾 CRUD-JDBC com PreparedStatement
- ✅ 6 Tabelas Diferentes
- ✅ 15 Exercícios Práticos
- ✅ Padrão MVC
- ✅ PreparedStatement

## Contribuição

Siga as diretrizes do arquivo `CONTRIBUTING.md` para contribuir com o projeto.

## Licença

Este projeto está licenciado sob a licença especificada no arquivo `LICENSE`.

---

**Desenvolvido por** CaduBraga  
**Turma MIDS 77** - Curso de Desenvolvimento de Sistemas  
**Professor**: Vinícius Trindade