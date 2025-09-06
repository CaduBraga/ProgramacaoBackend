package org.example.service;

import java.util.Scanner;
import org.example.service.dao.*;

public class Create {

    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private final LivrosDAO livrosDAO = new LivrosDAO();
    private final PedidosDAO pedidosDAO = new PedidosDAO();
    private final ProdutosDAO produtosDAO = new ProdutosDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    private final Scanner input = new Scanner(System.in);

    public void action(int tabela) {
        switch (tabela) {
            case 1 -> {
                System.out.print("Nome: ");
                String nome = input.nextLine();
                System.out.print("Matrícula: ");
                String matricula = input.nextLine();
                System.out.print("Curso: ");
                String curso = input.nextLine();
                alunoDAO.create(nome, matricula, curso);
            }
            case 2 -> {
                System.out.print("Nome: ");
                String nome = input.nextLine();
                System.out.print("Cargo: ");
                String cargo = input.nextLine();
                System.out.print("Salário: ");
                double salario = input.nextDouble(); input.nextLine();
                funcionariosDAO.create(nome, cargo, salario);
            }
            case 3 -> {
                System.out.print("Título: ");
                String titulo = input.nextLine();
                System.out.print("Autor: ");
                String autor = input.nextLine();
                System.out.print("Ano de Publicação: ");
                int ano = input.nextInt(); input.nextLine();
                livrosDAO.create(titulo, autor, ano);
            }
            case 4 -> {
                System.out.print("Cliente: ");
                String cliente = input.nextLine();
                System.out.print("Data (yyyy-mm-dd): ");
                String data = input.nextLine();
                System.out.print("Total: ");
                double total = input.nextDouble(); input.nextLine();
                pedidosDAO.create(cliente, java.sql.Date.valueOf(data), total);
            }
            case 5 -> {
                System.out.print("Nome: ");
                String nome = input.nextLine();
                System.out.print("Preço: ");
                double preco = input.nextDouble();
                System.out.print("Quantidade: ");
                int qtd = input.nextInt(); input.nextLine();
                produtosDAO.create(nome, preco, qtd);
            }
            case 6 -> {
                System.out.print("Nome: ");
                String nome = input.nextLine();
                System.out.print("Email: ");
                String email = input.nextLine();
                usuarioDAO.create(nome, email);
            }
            default -> System.out.println("Tabela inválida.");
        }
    }
}