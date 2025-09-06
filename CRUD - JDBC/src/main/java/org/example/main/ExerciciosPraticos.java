package org.example.main;

import java.util.List;
import java.util.Scanner;

import org.example.service.dao.AlunoDAO;
import org.example.service.dao.FuncionariosDAO;
import org.example.service.dao.LivrosDAO;
import org.example.service.dao.PedidosDAO;
import org.example.service.dao.ProdutosDAO;
import org.example.service.dao.UsuarioDAO;

public class ExerciciosPraticos {
    
    private static final Scanner input = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("=== EXERCÍCIOS PRÁTICOS COM PREPAREDSTATEMENT ===\n");
        
        while (true) {
            exibirMenu();
            int opcao = input.nextInt();
            input.nextLine();
            
            if (opcao == 0) {
                System.out.println("Saindo...");
                break;
            }
            
            executarExercicio(opcao);
            System.out.println("\n" + "=".repeat(50) + "\n");
        }
        
        input.close();
    }
    
    private static void exibirMenu() {
        System.out.println("Escolha um exercício:");
        System.out.println("1. Atualizar curso de um aluno (por matrícula)");
        System.out.println("2. Atualizar preço de um produto (por nome)");
        System.out.println("3. Atualizar valor total de um pedido (por ID)");
        System.out.println("4. Atualizar autor de um livro (por título)");
        System.out.println("5. Atualizar salário de um funcionário (por nome)");
        System.out.println("6. Atualizar email de um usuário (por nome)");
        System.out.println("7. Deletar aluno (por matrícula)");
        System.out.println("8. Deletar produto (por nome)");
        System.out.println("9. Deletar pedido (por ID)");
        System.out.println("10. Deletar livro (por título)");
        System.out.println("11. Deletar funcionário (por nome)");
        System.out.println("12. Deletar usuário (por nome)");
        System.out.println("13. Listar todos os usuários");
        System.out.println("14. Buscar usuário por ID");
        System.out.println("15. Filtrar usuários por domínio de email");
        System.out.println("16. Contar total de usuários");
        System.out.println("0. Sair");
        System.out.print("Opção: ");
    }
    
    private static void executarExercicio(int opcao) {
        switch (opcao) {
            case 1 -> exercicioAtualizarCursoAluno();
            case 2 -> exercicioAtualizarPrecoProduto();
            case 3 -> exercicioAtualizarValorTotalPedido();
            case 4 -> exercicioAtualizarAutorLivro();
            case 5 -> exercicioAtualizarSalarioFuncionario();
            case 6 -> exercicioAtualizarEmailUsuario();
            case 7 -> exercicioDeletarAluno();
            case 8 -> exercicioDeletarProduto();
            case 9 -> exercicioDeletarPedido();
            case 10 -> exercicioDeletarLivro();
            case 11 -> exercicioDeletarFuncionario();
            case 12 -> exercicioDeletarUsuario();
            case 13 -> exercicioListarUsuarios();
            case 14 -> exercicioBuscarUsuarioPorId();
            case 15 -> exercicioFiltrarUsuariosPorDominio();
            case 16 -> exercicioContarUsuarios();
            default -> System.out.println("Opção inválida!");
        }
    }

    private static void exercicioListarUsuarios() {
        System.out.println("\n--- Listar Todos os Usuários ---");
        
        List<UsuarioDAO.Usuario> usuarios = UsuarioDAO.listar();
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado.");
        } else {
            System.out.println("Usuários cadastrados:");
            for (UsuarioDAO.Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    private static void exercicioBuscarUsuarioPorId() {
        System.out.println("\n--- Buscar Usuário por ID ---");
        System.out.print("Digite o ID do usuário: ");
        int id = input.nextInt();
        
        UsuarioDAO.Usuario usuario = UsuarioDAO.listarPorId(id);
        
        if (usuario.getId() != 0) {
            System.out.println("Usuário encontrado:");
            System.out.println(usuario);
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private static void exercicioFiltrarUsuariosPorDominio() {
        System.out.println("\n--- Filtrar Usuários por Domínio de Email ---");
        System.out.print("Digite o domínio (ex: @email.com): ");
        String dominio = input.nextLine();
        
        List<UsuarioDAO.Usuario> usuarios = UsuarioDAO.listarPorDominio(dominio);
        
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário encontrado com esse domínio.");
        } else {
            System.out.println("Usuários com email terminando em '" + dominio + "':");
            for (UsuarioDAO.Usuario usuario : usuarios) {
                System.out.println(usuario);
            }
        }
    }

    private static void exercicioContarUsuarios() {
        System.out.println("\n--- Contar Total de Usuários ---");
        
        int total = UsuarioDAO.contarUsuarios();
        System.out.println("Total de usuários cadastrados: " + total);
    }

    private static void exercicioAtualizarCursoAluno() {
        System.out.println("\n--- Atualizar Curso de um Aluno ---");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = input.nextLine();
        System.out.print("Digite o novo curso: ");
        String novoCurso = input.nextLine();

        AlunoDAO alunoDAO = new AlunoDAO();
        boolean sucesso = alunoDAO.atualizarCurso(matricula, novoCurso);

        if (sucesso) {
            System.out.println("✅ Curso atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar curso.");
        }
    }

    private static void exercicioAtualizarPrecoProduto() {
        System.out.println("\n--- Atualizar Preço de um Produto ---");
        System.out.print("Digite o nome do produto: ");
        String nome = input.nextLine();
        System.out.print("Digite o novo preço: ");
        double novoPreco = input.nextDouble();

        ProdutosDAO produtosDAO = new ProdutosDAO();
        boolean sucesso = produtosDAO.atualizarPreco(nome, novoPreco);

        if (sucesso) {
            System.out.println("✅ Preço atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar preço.");
        }
    }

    private static void exercicioAtualizarValorTotalPedido() {
        System.out.println("\n--- Atualizar Valor Total de um Pedido ---");
        System.out.print("Digite o ID do pedido: ");
        int id = input.nextInt();
        System.out.print("Digite o novo valor total: ");
        double novoTotal = input.nextDouble();

        PedidosDAO pedidosDAO = new PedidosDAO();
        boolean sucesso = pedidosDAO.atualizarValorTotal(id, novoTotal);

        if (sucesso) {
            System.out.println("✅ Valor total atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar valor total.");
        }
    }

    private static void exercicioAtualizarAutorLivro() {
        System.out.println("\n--- Atualizar Autor de um Livro ---");
        System.out.print("Digite o título do livro: ");
        String titulo = input.nextLine();
        System.out.print("Digite o novo autor: ");
        String novoAutor = input.nextLine();

        LivrosDAO livrosDAO = new LivrosDAO();
        boolean sucesso = livrosDAO.atualizarAutor(titulo, novoAutor);

        if (sucesso) {
            System.out.println("✅ Autor atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar autor.");
        }
    }

    private static void exercicioAtualizarSalarioFuncionario() {
        System.out.println("\n--- Atualizar Salário de um Funcionário ---");
        System.out.print("Digite o nome do funcionário: ");
        String nome = input.nextLine();
        System.out.print("Digite o novo salário: ");
        double novoSalario = input.nextDouble();

        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        boolean sucesso = funcionariosDAO.atualizarSalario(nome, novoSalario);

        if (sucesso) {
            System.out.println("✅ Salário atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar salário.");
        }
    }

    private static void exercicioAtualizarEmailUsuario() {
        System.out.println("\n--- Atualizar Email de um Usuário ---");
        System.out.print("Digite o nome do usuário: ");
        String nome = input.nextLine();
        System.out.print("Digite o novo email: ");
        String novoEmail = input.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.atualizarEmail(nome, novoEmail);

        if (sucesso) {
            System.out.println("✅ Email atualizado com sucesso!");
        } else {
            System.out.println("❌ Falha ao atualizar email.");
        }
    }

    private static void exercicioDeletarAluno() {
        System.out.println("\n--- Deletar Aluno ---");
        System.out.print("Digite a matrícula do aluno: ");
        String matricula = input.nextLine();

        AlunoDAO alunoDAO = new AlunoDAO();
        boolean sucesso = alunoDAO.deletarAluno(matricula);

        if (sucesso) {
            System.out.println("✅ Aluno deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar aluno.");
        }
    }

    private static void exercicioDeletarProduto() {
        System.out.println("\n--- Deletar Produto ---");
        System.out.print("Digite o nome do produto: ");
        String nome = input.nextLine();

        ProdutosDAO produtosDAO = new ProdutosDAO();
        boolean sucesso = produtosDAO.deletarProduto(nome);

        if (sucesso) {
            System.out.println("✅ Produto deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar produto.");
        }
    }

    private static void exercicioDeletarPedido() {
        System.out.println("\n--- Deletar Pedido ---");
        System.out.print("Digite o ID do pedido: ");
        int id = input.nextInt();

        PedidosDAO pedidosDAO = new PedidosDAO();
        boolean sucesso = pedidosDAO.delete(id);

        if (sucesso) {
            System.out.println("✅ Pedido deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar pedido.");
        }
    }

    private static void exercicioDeletarLivro() {
        System.out.println("\n--- Deletar Livro ---");
        System.out.print("Digite o título do livro: ");
        String titulo = input.nextLine();

        LivrosDAO livrosDAO = new LivrosDAO();
        boolean sucesso = livrosDAO.deletarLivro(titulo);

        if (sucesso) {
            System.out.println("✅ Livro deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar livro.");
        }
    }

    private static void exercicioDeletarFuncionario() {
        System.out.println("\n--- Deletar Funcionário ---");
        System.out.print("Digite o nome do funcionário: ");
        String nome = input.nextLine();

        FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
        boolean sucesso = funcionariosDAO.deletarFuncionario(nome);

        if (sucesso) {
            System.out.println("✅ Funcionário deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar funcionário.");
        }
    }

    private static void exercicioDeletarUsuario() {
        System.out.println("\n--- Deletar Usuário ---");
        System.out.print("Digite o nome do usuário: ");
        String nome = input.nextLine();

        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean sucesso = usuarioDAO.deletarUsuario(nome);

        if (sucesso) {
            System.out.println("✅ Usuário deletado com sucesso!");
        } else {
            System.out.println("❌ Falha ao deletar usuário.");
        }
    }
}