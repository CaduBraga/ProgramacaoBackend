# Guia de Contribuição

Obrigado por considerar contribuir para este projeto educacional **CRUD-JDBC**!

## Como Contribuir

### 1. Fork e Clone

1. Faça um fork deste repositório
2. Clone o fork para sua máquina local:
   ```bash
   git clone https://github.com/seu-usuario/CRUD-JDBC.git
   ```

### 2. Crie uma Branch

Crie uma branch para sua contribuição:
```bash
git checkout -b feature/nova-funcionalidade
```

### 3. Desenvolva

- Mantenha o código limpo e bem documentado
- Siga os padrões de nomenclatura Java
- Adicione comentários explicativos quando necessário
- Teste seu código antes de submeter
- Mantenha a estrutura MVC do projeto
- Use PreparedStatement para operações SQL

### 4. Commit

Faça commits com mensagens descritivas:
```bash
git commit -m "Adiciona nova funcionalidade X"
```

### 5. Push e Pull Request

1. Faça push para sua branch:
   ```bash
   git push origin feature/nova-funcionalidade
   ```

2. Crie um Pull Request no GitHub

## Padrões de Código

### Java
- Use camelCase para variáveis e métodos
- Use PascalCase para classes
- Use UPPER_CASE para constantes
- Mantenha linhas com no máximo 80 caracteres
- Use 4 espaços para indentação

### Estrutura do Projeto CRUD-JDBC
```
src/main/java/org/example/
├── config/
│   └── Conexao.java          # Configuração de conexão
├── main/
│   └── Main.java             # Ponto de entrada
├── service/
│   ├── Gerenciamento.java    # Controlador principal
│   ├── Create.java           # Operações de criação
│   ├── Read.java             # Operações de leitura
│   ├── Update.java           # Operações de atualização
│   ├── Delete.java           # Operações de exclusão
│   └── dao/                  # Data Access Objects
│       ├── AlunoDAO.java
│       ├── FuncionariosDAO.java
│       ├── LivrosDAO.java
│       ├── PedidosDAO.java
│       ├── ProdutosDAO.java
│       └── UsuarioDAO.java
└── view/
    └── MainView.java         # Interface do usuário
```

### Comentários
```java
/**
 * Classe que representa um exemplo
 * @author CaduBraga
 * @version 1.0
 */
public class Exemplo {
    // Comentário de linha única
    
    /*
     * Comentário de múltiplas linhas
     * para explicar lógica complexa
     */
}
```

### SQL e JDBC
- Use PreparedStatement para prevenir SQL Injection
- Sempre feche conexões e statements
- Trate exceções adequadamente
- Use try-with-resources quando possível

## Tipos de Contribuição

### 🐛 Correção de Bugs
- Descreva o bug claramente
- Inclua passos para reproduzir
- Adicione testes se possível
- Verifique se afeta as operações CRUD

### ✨ Novas Funcionalidades
- Explique a funcionalidade proposta
- Mantenha consistência com o projeto existente
- Adicione documentação
- Considere o impacto na arquitetura MVC

### 📚 Melhorias na Documentação
- Corrija erros de gramática
- Adicione exemplos de uso
- Melhore a clareza
- Documente novas funcionalidades

### 🎨 Melhorias de Código
- Refatore código existente
- Melhore performance
- Adicione validações
- Otimize consultas SQL

### 🗄️ Melhorias no Banco de Dados
- Adicione novas tabelas se necessário
- Otimize consultas existentes
- Adicione índices quando apropriado
- Mantenha a integridade dos dados

## Processo de Review

1. **Análise de Código**: Seu código será revisado
2. **Testes**: Verifique se tudo funciona
3. **Documentação**: Atualize documentação se necessário
4. **Merge**: Após aprovação, será mergeado

## Código de Conduta

- Seja respeitoso e construtivo
- Ajude outros desenvolvedores
- Mantenha o foco educacional do projeto
- Reporte problemas de forma profissional
- Compartilhe conhecimento sobre JDBC e MySQL

## Tecnologias do Projeto

- **Java 22** - Linguagem de programação
- **Maven** - Gerenciamento de dependências
- **MySQL** - Banco de dados
- **JDBC** - API para conexão com banco de dados
- **PreparedStatement** - Prevenção de SQL Injection

## Contato

Para dúvidas ou sugestões:
- Abra uma issue no GitHub
- Entre em contato com o autor

---

**Obrigado por contribuir para o aprendizado sobre JDBC e programação Java!** 🎓

**Desenvolvido por** CaduBraga  
**Turma MIDS 77** - Curso de Desenvolvimento de Sistemas  
**Professor**: Vinícius Trindade