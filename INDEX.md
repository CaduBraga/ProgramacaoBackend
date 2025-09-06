# Índice de Projetos - Programação Backend

Este repositório contém três projetos práticos de programação backend em Java utilizando JDBC, Maven e MySQL.

## 📋 Lista de Projetos

### 1. 🚚 Sistema de Logística de Entregas
**Localização**: `Sistema de logística e entregas/`  
**Complexidade**: Avançado  
**Funcionalidades**: 16 opções completas

**Características:**
- Sistema completo para gestão de entregas de e-commerce
- Cadastro de clientes, motoristas, pedidos e entregas
- Gestão de histórico de entregas com eventos
- Relatórios analíticos avançados
- Validações de dependência para exclusões
- Arquitetura em camadas (Model, DAO, Service, View)

**Tecnologias:**
- Java 11+
- Maven
- MySQL 8.0+
- JDBC com PreparedStatement

**Como executar:**
```bash
cd "Sistema de logística e entregas"
mvn clean compile
mvn exec:java
```

---

### 2. 📞 Sistema de Lista Telefônica
**Localização**: `Lista telefônica/`  
**Complexidade**: Básico  
**Funcionalidades**: 6 opções (CRUD completo)

**Características:**
- Sistema simples para gerenciamento de contatos
- CRUD completo (Create, Read, Update, Delete)
- Busca por nome com filtros
- Interface de usuário via console
- Estrutura em camadas (model, dao, util, app)
- Padrão POO implementado

**Tecnologias:**
- Java 11+
- Maven
- MySQL 8.0+
- JDBC

**Como executar:**
```bash
cd "Lista telefônica"
mvn clean compile
mvn exec:java
```

---

### 3. 💾 CRUD-JDBC com PreparedStatement
**Localização**: `CRUD - JDBC/`  
**Complexidade**: Intermediário  
**Funcionalidades**: 15 exercícios práticos

**Características:**
- Exercícios práticos de JDBC
- Operações CRUD para 6 tabelas diferentes
- Padrão MVC implementado
- Interface de usuário interativa
- Uso de PreparedStatement para segurança
- Exercícios de UPDATE e DELETE

**Tecnologias:**
- Java 11+
- Maven
- MySQL 8.0+
- JDBC com PreparedStatement

**Como executar:**
```bash
cd "CRUD - JDBC"
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.main.Main"
```

---

## 🗂️ Estrutura do Repositório

```
ProgramacaoBackend/
├── Sistema de logística e entregas/     # Sistema completo de logística
│   ├── src/main/java/com/logistica/     # Código fonte
│   ├── src/main/resources/schema.sql    # Script do banco
│   ├── pom.xml                          # Configuração Maven
│   └── README.md                        # Documentação específica
├── Lista telefônica/                    # Sistema de lista telefônica
│   ├── src/main/java/com/lista/         # Código fonte
│   ├── schema.sql                       # Script do banco
│   ├── pom.xml                          # Configuração Maven
│   └── README.md                        # Documentação específica
├── CRUD - JDBC/                        # Exercícios práticos de JDBC
│   ├── src/main/java/org/example/       # Código fonte
│   ├── schema.sql                       # Script do banco
│   └── README.md                        # Documentação específica
├── README.md                           # Documentação principal
├── CHANGELOG.md                        # Histórico de mudanças
├── CONTRIBUTING.md                     # Diretrizes de contribuição
├── CODE_OF_CONDUCT.md                  # Código de conduta
├── LICENSE                             # Licença do projeto
└── INDEX.md                            # Este arquivo
```

## 🚀 Início Rápido

### Pré-requisitos
- Java 11 ou superior
- Maven 3.6 ou superior
- MySQL 8.0 ou superior

### Configuração Inicial
1. Clone o repositório
2. Configure o MySQL com as credenciais padrão (root/123456)
3. Execute os scripts SQL fornecidos em cada projeto
4. Escolha um projeto e siga as instruções de execução

### Scripts SQL
- **Logística**: `Sistema de logística e entregas/src/main/resources/schema.sql`
- **Lista Telefônica**: `Lista telefônica/schema.sql`
- **CRUD-JDBC**: `CRUD - JDBC/schema.sql`

## 📚 Aprendizado

### Para Iniciantes
Recomenda-se começar com o **Sistema de Lista Telefônica** para entender os conceitos básicos de:
- POO (Programação Orientada a Objetos)
- JDBC básico
- CRUD simples
- Estrutura de projetos Java

### Para Intermediários
O **CRUD-JDBC com PreparedStatement** oferece:
- Exercícios práticos de JDBC
- Padrão MVC
- PreparedStatement para segurança
- Múltiplas tabelas para prática

### Para Avançados
O **Sistema de Logística de Entregas** demonstra:
- Arquitetura em camadas
- Relatórios complexos com JOINs
- Validações de negócio
- Gestão de dependências
- Interface completa

## 🔧 Configuração de Desenvolvimento

### IDE Recomendada
- IntelliJ IDEA
- Eclipse
- VS Code com extensões Java

### Configurações Maven
Todos os projetos usam Maven com as mesmas dependências básicas:
- MySQL Connector/J 8.0.33
- Java 11
- UTF-8 encoding

### Banco de Dados
- **Host**: localhost
- **Port**: 3306
- **User**: root
- **Password**: 123456

## 📞 Suporte

Para dúvidas ou problemas:
1. Verifique a documentação específica de cada projeto
2. Consulte o CHANGELOG.md para mudanças recentes
3. Siga as diretrizes do CONTRIBUTING.md para contribuições

---

**Desenvolvido por** CaduBraga  
**Turma MIDS 77** - Curso de Desenvolvimento de Sistemas  
**Professor**: Vinícius Trindade
