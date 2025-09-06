package org.example.main;

import java.util.Scanner;

import org.example.service.Gerenciamento;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        System.out.println("=== SISTEMA CRUD-JDBC ===");
        System.out.println("Escolha uma opção:");
        System.out.println("1. Sistema CRUD Principal");
        System.out.println("2. Exercícios Práticos com PreparedStatement");
        System.out.print("Opção: ");
        
        int opcao = input.nextInt();
        
        switch (opcao) {
            case 1 -> new Gerenciamento().iniciar();
            case 2 -> ExerciciosPraticos.main(args);
            default -> System.out.println("Opção inválida!");
        }
        
        input.close();
    }
}