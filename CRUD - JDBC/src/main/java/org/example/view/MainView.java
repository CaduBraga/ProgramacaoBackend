package org.example.view;

import java.util.Scanner;

public class MainView {
    private final Scanner input = new Scanner(System.in);

    public int menuTabela() {
        System.out.println("\n=== SEJA BEM VINDO AO CRUD COM JDBC ===");
        System.out.println("Escolha a tabela:");
        System.out.println("1 - Alunos");
        System.out.println("2 - Funcionários");
        System.out.println("3 - Livros");
        System.out.println("4 - Pedidos");
        System.out.println("5 - Produtos");
        System.out.println("6 - Usuários");
        System.out.println("0 - Sair");
        System.out.print("> ");
        return input.nextInt();
    }

    public int menuAcao() {
        System.out.println("O que você deseja fazer?");
        System.out.println("1 - Create");
        System.out.println("2 - Read");
        System.out.println("3 - Update");
        System.out.println("4 - Delete");
        System.out.print("> ");
        return input.nextInt();
    }

    public int solicitarInteiro(String msg) {
        System.out.print(msg);
        return input.nextInt();
    }

    public double solicitarDouble(String msg) {
        System.out.print(msg);
        return input.nextDouble();
    }

    public void exibirMensagem(String msg) {
        System.out.println(msg); // método para printar msg
    }
}
