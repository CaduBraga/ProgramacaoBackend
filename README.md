# CRUD-JDBC com PreparedStatement

Sistema CRUD completo em Java utilizando JDBC com PreparedStatement para operações seguras no banco de dados.

## Funcionalidades Implementadas

### Sistema CRUD Principal
- **Create**: Inserir registros nas tabelas
- **Read**: Visualizar registros das tabelas
- **Update**: Atualizar registros existentes
- **Delete**: Remover registros das tabelas

### Exercícios Práticos com PreparedStatement

#### Exercícios de Atualização (UPDATE)
1. **Exercício 6**: Atualizar curso de um aluno com base na matrícula
2. **Exercício 7**: Atualizar preço de um produto com base no nome
3. **Exercício 8**: Atualizar valor total de um pedido com base no ID
4. **Exercício 9**: Atualizar autor de um livro com base no título
5. **Exercício 10**: Atualizar salário de um funcionário com base no nome

#### Exercícios de Deleção (DELETE)
6. **Exercício 11**: Deletar aluno com base na matrícula
7. **Exercício 12**: Deletar produto com base no nome
8. **Exercício 13**: Deletar pedido com base no ID
9. **Exercício 14**: Deletar livro com base no título
10. **Exercício 15**: Deletar funcionário com base no nome

#### Atividades Práticas com SELECT
11. **Atividade 1**: Listar todos os usuários
12. **Atividade 2**: Buscar um usuário por ID
13. **Atividade 3**: Exibir usuários com emails de um domínio específico
14. **Atividade 4**: Contar quantos usuários estão cadastrados

## Estrutura do Projeto

```
src/main/java/org/example/
├── config/
│   └── Conexao.java          # Configuração de conexão com banco
├── main/
│   ├── Main.java             # Classe principal com menu de opções
│   └── ExerciciosPraticos.java # Demonstração dos exercícios
├── service/
│   ├── Create.java           # Operações de criação
│   ├── Read.java             # Operações de leitura
│   ├── Update.java           # Operações de atualização
│   ├── Delete.java           # Operações de deleção
│   ├── Gerenciamento.java    # Gerenciador principal
│   └── dao/                  # Data Access Objects
│       ├── AlunoDAO.java
│       ├── FuncionariosDAO.java
│       ├── LivrosDAO.java
│       ├── PedidosDAO.java
│       ├── ProdutosDAO.java
│       └── UsuarioDAO.java
└── view/
    └── MainView.java         # Interface de usuário
```

## Como Executar

### 1. Configuração do Banco
- Execute o script `resources/db/database.sql` no seu banco MySQL
- Configure a conexão em `src/main/java/org/example/config/Conexao.java`

### 2. Compilação e Execução
```bash
# Compilar o projeto
mvn compile

# Executar
mvn exec:java -Dexec.mainClass="org.example.main.Main"
```

### 3. Menu Principal
Ao executar, você verá:
```
=== SISTEMA CRUD-JDBC ===
Escolha uma opção:
1. Sistema CRUD Principal
2. Exercícios Práticos com PreparedStatement
```

## Exemplos de Uso

### Atualizar Email de Usuário
```java
UsuarioDAO usuarioDAO = new UsuarioDAO();
boolean sucesso = usuarioDAO.atualizarEmail("Ana Souza", "ana.nova@email.com");
```

### Deletar Produto por Nome
```java
ProdutosDAO produtosDAO = new ProdutosDAO();
boolean sucesso = produtosDAO.deletarProduto("Produto A");
```

### Listar Usuários por Domínio
```java
List<UsuarioDAO.Usuario> usuarios = UsuarioDAO.listarPorDominio("@email.com");
for (UsuarioDAO.Usuario usuario : usuarios) {
    System.out.println(usuario);
}
```

### Contar Total de Usuários
```java
int total = UsuarioDAO.contarUsuarios();
System.out.println("Total: " + total);
```

## Características dos Exercícios

- **PreparedStatement**: Todos os métodos utilizam PreparedStatement para segurança
- **Tratamento de Erros**: Implementação robusta com try-catch e mensagens informativas
- **Validação**: Verificação de linhas afetadas para confirmar operações
- **Interface Amigável**: Menu interativo para testar todas as funcionalidades
- **Código Limpo**: Implementação seguindo boas práticas de programação

## Tabelas do Banco

- **usuarios**: id, nome, email
- **alunos**: id, nome, matricula, curso
- **funcionarios**: id, nome, cargo, salario
- **livros**: id, titulo, autor, ano_publicacao
- **pedidos**: id, cliente, data_pedido, total
- **produtos**: id, nome, preco, quantidade

## Dados de Teste

O sistema inclui dados de teste na tabela `usuarios` com 10 registros para prática das operações.

## Contribuição

Siga as diretrizes do arquivo `CONTRIBUTING.md` para contribuir com o projeto.

## Licença

Este projeto está licenciado sob a licença especificada no arquivo `LICENSE`.