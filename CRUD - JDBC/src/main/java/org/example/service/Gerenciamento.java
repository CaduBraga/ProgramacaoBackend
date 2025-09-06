package org.example.service;

import org.example.view.MainView;

public class Gerenciamento {

    private final MainView view = new MainView();

    private final Create create = new Create();
    private final Read read = new Read();
    private final Update update = new Update();
    private final Delete delete = new Delete();

    public void iniciar() {
        while (true) {
            int tabela = view.menuTabela();
            if (tabela == 0) break;

            int acao = view.menuAcao();

            switch (acao) {
                case 1 -> create.action(tabela);
                case 2 -> read.action(tabela);
                case 3 -> update.action(tabela);
                case 4 -> delete.action(tabela);
                default -> view.exibirMensagem("Ação inválida.");
            }
        }

        view.exibirMensagem("Programa encerrado.");
    }
}