package org.example.service;

import org.example.view.MainView;
import org.example.service.dao.*;

public class Delete {

    private final MainView view = new MainView();

    private final ProdutosDAO produtosDAO = new ProdutosDAO();
    private final PedidosDAO pedidosDAO = new PedidosDAO();
    private final LivrosDAO livrosDAO = new LivrosDAO();
    private final FuncionariosDAO funcionariosDAO = new FuncionariosDAO();
    private final AlunoDAO alunoDAO = new AlunoDAO();
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();

    public void action(int tabela) {
        int id = view.solicitarInteiro("Informe o ID para exclusão: ");
        boolean sucesso = false;

        switch (tabela) {
            case 1 -> sucesso = produtosDAO.delete(id);
            case 2 -> sucesso = pedidosDAO.delete(id);
            case 3 -> sucesso = livrosDAO.delete(id);
            case 4 -> sucesso = funcionariosDAO.delete(id);
            case 5 -> sucesso = alunoDAO.delete(id);
            case 6 -> sucesso = usuarioDAO.delete(id);
            default -> view.exibirMensagem("Opção inválida.");
        }

        if (sucesso) {
            view.exibirMensagem("Registro deletado com sucesso!");
        } else {
            view.exibirMensagem("Erro ao deletar registro.");
        }
    }
}
