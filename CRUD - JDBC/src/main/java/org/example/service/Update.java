package org.example.service;

import org.example.config.Conexao;
import org.example.service.dao.*;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Update {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private final LivrosDAO livrosDAO = new LivrosDAO();
    private final PedidosDAO pedidosDAO = new PedidosDAO();
    private final ProdutosDAO produtosDAO = new ProdutosDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private final Scanner input = new Scanner(System.in);

    public void action(int tabela) {
        System.out.print("ID do registro a ser atualizado: ");
        int id = input.nextInt();
        input.nextLine();

        switch (tabela) {
            case 1 -> {
                System.out.print("Novo nome: ");
                String nome = input.nextLine();
                System.out.print("Nova matrícula: ");
                String matricula = input.nextLine();
                System.out.print("Novo curso: ");
                String curso = input.nextLine();
                alunoDAO.update(id, nome, matricula, curso);
            }
            case 2 -> {
                System.out.print("Novo nome: ");
                String nome = input.nextLine();
                System.out.print("Novo cargo: ");
                String cargo = input.nextLine();
                System.out.print("Novo salário: ");
                double salario = input.nextDouble();
                input.nextLine();
                funcionariosDAO.update(id, nome, cargo, salario);
            }
            case 3 -> {
                System.out.print("Novo título: ");
                String titulo = input.nextLine();
                System.out.print("Novo autor: ");
                String autor = input.nextLine();
                System.out.print("Novo ano de publicação: ");
                int ano = input.nextInt();
                input.nextLine();
                livrosDAO.update(id, titulo, autor, ano);
            }
            case 4 -> {
                System.out.print("Novo cliente: ");
                String cliente = input.nextLine();
                System.out.print("Nova data (yyyy-mm-dd): ");
                String data = input.nextLine();
                System.out.print("Novo total: ");
                double total = input.nextDouble();
                input.nextLine();
                pedidosDAO.update(id, cliente, Date.valueOf(data), total);
            }
            case 5 -> {
                System.out.print("Novo nome: ");
                String nome = input.nextLine();
                System.out.print("Novo preço: ");
                double preco = input.nextDouble();
                System.out.print("Nova quantidade: ");
                int quantidade = input.nextInt();
                input.nextLine();
                produtosDAO.update(id, nome, preco, quantidade);
            }
            case 6 -> {
                System.out.print("Novo nome: ");
                String nome = input.nextLine();
                System.out.print("Novo e-mail: ");
                String email = input.nextLine();
                usuarioDAO.update(id, nome, email);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }
}
