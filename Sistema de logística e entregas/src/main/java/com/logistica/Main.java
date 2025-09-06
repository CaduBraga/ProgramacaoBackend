package com.logistica;

import com.logistica.config.DatabaseConfig;
import com.logistica.view.MenuView;

public class Main {
    
    public static void main(String[] args) {
        System.out.println("Iniciando Sistema de Logística de Entregas...");
        
        if (!DatabaseConfig.testConnection()) {
            System.err.println("Erro: Não foi possível conectar ao banco de dados!");
            System.err.println("Verifique se o MySQL está rodando e as configurações estão corretas.");
            return;
        }
        
        System.out.println("Conexão com banco de dados estabelecida com sucesso!");
        
        MenuView menu = new MenuView();
        menu.exibirMenu();
    }
}
