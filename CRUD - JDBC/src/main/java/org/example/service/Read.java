package org.example.service;

import org.example.view.MainView;
import org.example.service.dao.*;

public class Read {

    private final MainView view = new MainView();

    private final ProdutosDAO produtosDAO = new ProdutosDAO();
    private final PedidosDAO pedidosDAO = new PedidosDAO();
    private final LivrosDAO livrosDAO = new LivrosDAO();
    private final FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void action(int tabela) {
        switch (tabela) {
            case 1 -> produtosDAO.read();
            case 2 -> pedidosDAO.read();
            case 3 -> livrosDAO.read();
            case 4 -> funcionariosDAO.read();
            case 5 -> alunoDAO.read();
            case 6 -> usuarioDAO.read();
            default -> view.exibirMensagem("Opção inválida.");
        }
    }
}
