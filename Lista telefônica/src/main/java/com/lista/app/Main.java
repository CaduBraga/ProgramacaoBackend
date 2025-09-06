package com.lista.app;

import com.lista.dao.ContatoDAO;
import com.lista.model.Contato;
import com.lista.util.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    private static Scanner scanner = new Scanner(System.in);
    private static ContatoDAO contatoDAO = new ContatoDAO();
    
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE LISTA TELEFÔNICA ===");
        
        if (!testarConexao()) {
            System.err.println("Erro: Não foi possível conectar ao banco de dados!");
            System.err.println("Verifique se o MySQL está rodando e as configurações estão corretas.");
            return;
        }
        
        System.out.println("Conexão com banco de dados estabelecida com sucesso!");
        
        int opcao;
        do {
            exibirMenu();
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 6);
        
        System.out.println("Sistema encerrado. Obrigado!");
        scanner.close();
    }
    
    private static boolean testarConexao() {
        try (Connection conn = ConnectionFactory.getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
            return false;
        }
    }
    
    private static void exibirMenu() {
        System.out.println("\n=== MENU ===");
        System.out.println("1 - Cadastrar contato");
        System.out.println("2 - Listar todos os contatos");
        System.out.println("3 - Buscar contato por nome");
        System.out.println("4 - Atualizar dados de um contato");
        System.out.println("5 - Remover contato");
        System.out.println("6 - Sair do sistema");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void processarOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                cadastrarContato();
                break;
            case 2:
                listarContatos();
                break;
            case 3:
                buscarContatoPorNome();
                break;
            case 4:
                atualizarContato();
                break;
            case 5:
                removerContato();
                break;
            case 6:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private static void cadastrarContato() throws SQLException {
        System.out.println("\n=== CADASTRAR CONTATO ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Observação: ");
        String observacao = scanner.nextLine();
        
        Contato contato = new Contato(nome, telefone, email, observacao);
        int id = contatoDAO.salvar(contato);
        
        if (id > 0) {
            System.out.println("Contato cadastrado com sucesso! ID: " + id);
        } else {
            System.out.println("Erro ao cadastrar contato!");
        }
    }
    
    private static void listarContatos() throws SQLException {
        System.out.println("\n=== TODOS OS CONTATOS ===");
        
        List<Contato> contatos = contatoDAO.listar();
        
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado.");
            return;
        }
        
        System.out.printf("%-5s %-30s %-15s %-30s %-20s%n", "ID", "Nome", "Telefone", "Email", "Observação");
        System.out.println("------------------------------------------------------------------------------------------------");
        
        for (Contato contato : contatos) {
            System.out.printf("%-5d %-30s %-15s %-30s %-20s%n", 
                contato.getId(), 
                contato.getNome(), 
                contato.getTelefone(), 
                contato.getEmail() != null ? contato.getEmail() : "", 
                contato.getObservacao() != null ? contato.getObservacao() : "");
        }
    }
    
    private static void buscarContatoPorNome() throws SQLException {
        System.out.println("\n=== BUSCAR CONTATO POR NOME ===");
        
        System.out.print("Digite o nome (ou parte do nome): ");
        String nome = scanner.nextLine();
        
        List<Contato> contatos = contatoDAO.buscarTodosPorNome(nome);
        
        if (contatos.isEmpty()) {
            System.out.println("Nenhum contato encontrado com o nome: " + nome);
            return;
        }
        
        System.out.println("Contatos encontrados:");
        System.out.printf("%-5s %-30s %-15s %-30s %-20s%n", "ID", "Nome", "Telefone", "Email", "Observação");
        System.out.println("------------------------------------------------------------------------------------------------");
        
        for (Contato contato : contatos) {
            System.out.printf("%-5d %-30s %-15s %-30s %-20s%n", 
                contato.getId(), 
                contato.getNome(), 
                contato.getTelefone(), 
                contato.getEmail() != null ? contato.getEmail() : "", 
                contato.getObservacao() != null ? contato.getObservacao() : "");
        }
    }
    
    private static void atualizarContato() throws SQLException {
        System.out.println("\n=== ATUALIZAR CONTATO ===");
        
        System.out.print("Digite o ID do contato: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Contato contato = contatoDAO.buscarPorId(id);
        if (contato == null) {
            System.out.println("Contato não encontrado!");
            return;
        }
        
        System.out.println("Contato atual:");
        System.out.println("Nome: " + contato.getNome());
        System.out.println("Telefone: " + contato.getTelefone());
        System.out.println("Email: " + (contato.getEmail() != null ? contato.getEmail() : ""));
        System.out.println("Observação: " + (contato.getObservacao() != null ? contato.getObservacao() : ""));
        
        System.out.println("\nDigite os novos dados:");
        System.out.print("Novo telefone: ");
        String novoTelefone = scanner.nextLine();
        
        System.out.print("Novo email: ");
        String novoEmail = scanner.nextLine();
        
        System.out.print("Nova observação: ");
        String novaObservacao = scanner.nextLine();
        
        contato.setTelefone(novoTelefone);
        contato.setEmail(novoEmail);
        contato.setObservacao(novaObservacao);
        
        boolean sucesso = contatoDAO.atualizar(contato);
        
        if (sucesso) {
            System.out.println("Contato atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar contato!");
        }
    }
    
    private static void removerContato() throws SQLException {
        System.out.println("\n=== REMOVER CONTATO ===");
        
        System.out.print("Digite o ID do contato: ");
        int id = Integer.parseInt(scanner.nextLine());
        
        Contato contato = contatoDAO.buscarPorId(id);
        if (contato == null) {
            System.out.println("Contato não encontrado!");
            return;
        }
        
        System.out.println("Contato a ser removido:");
        System.out.println("Nome: " + contato.getNome());
        System.out.println("Telefone: " + contato.getTelefone());
        System.out.println("Email: " + (contato.getEmail() != null ? contato.getEmail() : ""));
        System.out.println("Observação: " + (contato.getObservacao() != null ? contato.getObservacao() : ""));
        
        System.out.print("Confirma a remoção? (s/n): ");
        String confirmacao = scanner.nextLine();
        
        if (confirmacao.equalsIgnoreCase("s")) {
            boolean sucesso = contatoDAO.deletar(id);
            
            if (sucesso) {
                System.out.println("Contato removido com sucesso!");
            } else {
                System.out.println("Erro ao remover contato!");
            }
        } else {
            System.out.println("Operação cancelada.");
        }
    }
}
