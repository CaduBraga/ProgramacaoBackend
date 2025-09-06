# Guia de Instalação e Configuração

Este guia irá ajudá-lo a configurar e executar os projetos de Programação Backend.

## 📋 Pré-requisitos

### Software Necessário
- **Java 11 ou superior**
- **Maven 3.6 ou superior**
- **MySQL 8.0 ou superior**
- **Git** (para clonar o repositório)

### Verificação de Instalações
```bash
# Verificar Java
java -version

# Verificar Maven
mvn -version

# Verificar MySQL
mysql --version
```

## 🚀 Instalação

### 1. Clonar o Repositório
```bash
git clone <url-do-repositorio>
cd ProgramacaoBackend
```

### 2. Configurar MySQL

#### Instalar MySQL
- **Windows**: Baixe o MySQL Installer do site oficial
- **Linux**: `sudo apt-get install mysql-server` (Ubuntu/Debian)
- **macOS**: `brew install mysql`

#### Configurar Banco de Dados
```sql
-- Conectar ao MySQL como root
mysql -u root -p

-- Criar usuário (opcional, se quiser usar credenciais específicas)
CREATE USER 'backend_user'@'localhost' IDENTIFIED BY 'backend_pass';
GRANT ALL PRIVILEGES ON *.* TO 'backend_user'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Executar Scripts SQL

#### Sistema de Logística de Entregas
```bash
mysql -u root -p < "Sistema de logística e entregas/src/main/resources/schema.sql"
```

#### Sistema de Lista Telefônica
```bash
mysql -u root -p < "Lista telefônica/schema.sql"
```

#### CRUD-JDBC
```bash
mysql -u root -p < "CRUD - JDBC/schema.sql"
```

## ⚙️ Configuração

### Credenciais do Banco de Dados

Se suas credenciais do MySQL forem diferentes das padrão, edite os arquivos de configuração:

#### Sistema de Logística de Entregas
Arquivo: `Sistema de logística e entregas/src/main/java/com/logistica/config/DatabaseConfig.java`
```java
private static final String URL = "jdbc:mysql://localhost:3306/logistica_entregas?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha_aqui";
```

#### Sistema de Lista Telefônica
Arquivo: `Lista telefônica/src/main/java/com/lista/util/ConnectionFactory.java`
```java
private static final String URL = "jdbc:mysql://localhost:3306/lista_telefonica?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha_aqui";
```

#### CRUD-JDBC
Arquivo: `CRUD - JDBC/src/main/java/org/example/config/Conexao.java`
```java
private static final String URL = "jdbc:mysql://localhost:3306/crud_jdbc?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
private static final String USER = "root";
private static final String PASSWORD = "sua_senha_aqui";
```

## 🏃‍♂️ Execução

### Método 1: Scripts Automatizados

#### Windows
```cmd
run.bat
```

#### Linux/macOS
```bash
chmod +x run.sh
./run.sh
```

### Método 2: Execução Manual

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

#### CRUD-JDBC
```bash
cd "CRUD - JDBC"
mvn clean compile
mvn exec:java -Dexec.mainClass="org.example.main.Main"
```

### Método 3: Executar Todos os Projetos
```bash
# Compilar todos os projetos
mvn clean compile

# Executar projeto específico
mvn exec:java -pl "Sistema de logística e entregas"
mvn exec:java -pl "Lista telefônica"
mvn exec:java -pl "CRUD - JDBC" -Dexec.mainClass="org.example.main.Main"
```

## 🔧 Solução de Problemas

### Erro de Conexão com MySQL
```
java.sql.SQLException: Access denied for user 'root'@'localhost'
```
**Solução**: Verifique as credenciais no arquivo de configuração

### Erro de Driver MySQL
```
java.lang.ClassNotFoundException: com.mysql.cj.jdbc.Driver
```
**Solução**: Execute `mvn clean compile` para baixar as dependências

### Erro de Banco Não Encontrado
```
Unknown database 'nome_do_banco'
```
**Solução**: Execute os scripts SQL fornecidos

### Erro de Porta MySQL
```
java.net.ConnectException: Connection refused
```
**Solução**: Verifique se o MySQL está rodando na porta 3306

## 📁 Estrutura de Arquivos

```
ProgramacaoBackend/
├── Sistema de logística e entregas/     # Sistema completo
├── Lista telefônica/                    # Sistema simples
├── CRUD - JDBC/                        # Exercícios práticos
├── run.bat                             # Script Windows
├── run.sh                              # Script Linux/macOS
├── pom.xml                             # Configuração Maven principal
├── README.md                           # Documentação principal
├── INDEX.md                            # Índice de projetos
├── INSTALL.md                          # Este arquivo
└── CHANGELOG.md                        # Histórico de mudanças
```

## 🆘 Suporte

### Logs de Erro
- Verifique a saída do console para mensagens de erro
- Logs do MySQL: `/var/log/mysql/error.log` (Linux) ou `C:\ProgramData\MySQL\MySQL Server 8.0\Data\*.err` (Windows)

### Verificação de Status
```bash
# Verificar se MySQL está rodando
sudo systemctl status mysql    # Linux
net start mysql                # Windows

# Verificar portas em uso
netstat -an | grep 3306        # Linux/macOS
netstat -an | findstr 3306     # Windows
```

### Contato
Para dúvidas ou problemas:
1. Verifique este guia primeiro
2. Consulte a documentação específica de cada projeto
3. Verifique o CHANGELOG.md para mudanças recentes

---

**Desenvolvido por** CaduBraga  
**Turma MIDS 77** - Curso de Desenvolvimento de Sistemas  
**Professor**: Vinícius Trindade
