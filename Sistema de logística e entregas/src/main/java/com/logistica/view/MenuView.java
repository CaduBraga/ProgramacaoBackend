package com.logistica.view;

import com.logistica.model.*;
import com.logistica.service.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class MenuView {
    
    private Scanner scanner;
    private ClienteService clienteService;
    private MotoristaService motoristaService;
    private PedidoService pedidoService;
    private EntregaService entregaService;
    
    public MenuView() {
        this.scanner = new Scanner(System.in);
        this.clienteService = new ClienteService();
        this.motoristaService = new MotoristaService();
        this.pedidoService = new PedidoService();
        this.entregaService = new EntregaService();
    }
    
    public void exibirMenu() {
        int opcao;
        do {
            System.out.println("\n=== SISTEMA DE LOGÍSTICA DE ENTREGAS ===");
            System.out.println("1 - Cadastrar Cliente");
            System.out.println("2 - Cadastrar Motorista");
            System.out.println("3 - Criar Pedido");
            System.out.println("4 - Atribuir Pedido a Motorista (Gerar Entrega)");
            System.out.println("5 - Registrar Evento de Entrega (Histórico)");
            System.out.println("6 - Atualizar Status da Entrega");
            System.out.println("7 - Listar Todas as Entregas com Cliente e Motorista");
            System.out.println("8 - Relatório: Total de Entregas por Motorista");
            System.out.println("9 - Relatório: Clientes com Maior Volume Entregue");
            System.out.println("10 - Relatório: Pedidos Pendentes por Estado");
            System.out.println("11 - Relatório: Entregas Atrasadas por Cidade");
            System.out.println("12 - Buscar Pedido por CPF/CNPJ do Cliente");
            System.out.println("13 - Cancelar Pedido");
            System.out.println("14 - Excluir Entrega (com validação)");
            System.out.println("15 - Excluir Cliente (com verificação de dependência)");
            System.out.println("16 - Excluir Motorista (com verificação de dependência)");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            
            try {
                opcao = Integer.parseInt(scanner.nextLine());
                processarOpcao(opcao);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida! Digite um número.");
                opcao = -1;
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
        
        System.out.println("Sistema encerrado. Obrigado!");
        scanner.close();
    }
    
    private void processarOpcao(int opcao) throws SQLException {
        switch (opcao) {
            case 1:
                cadastrarCliente();
                break;
            case 2:
                cadastrarMotorista();
                break;
            case 3:
                criarPedido();
                break;
            case 4:
                atribuirPedidoAMotorista();
                break;
            case 5:
                registrarEventoEntrega();
                break;
            case 6:
                atualizarStatusEntrega();
                break;
            case 7:
                listarTodasEntregas();
                break;
            case 8:
                relatorioTotalEntregasPorMotorista();
                break;
            case 9:
                relatorioClientesMaiorVolume();
                break;
            case 10:
                relatorioPedidosPendentesPorEstado();
                break;
            case 11:
                relatorioEntregasAtrasadasPorCidade();
                break;
            case 12:
                buscarPedidoPorCpfCnpj();
                break;
            case 13:
                cancelarPedido();
                break;
            case 14:
                excluirEntrega();
                break;
            case 15:
                excluirCliente();
                break;
            case 16:
                excluirMotorista();
                break;
            case 0:
                break;
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    private void cadastrarCliente() throws SQLException {
        System.out.println("\n=== CADASTRAR CLIENTE ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CPF/CNPJ: ");
        String cpfCnpj = scanner.nextLine();
        
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();
        
        Cliente cliente = new Cliente(nome, cpfCnpj, endereco, cidade, estado);
        Long id = clienteService.cadastrarCliente(cliente);
        
        System.out.println("Cliente cadastrado com sucesso! ID: " + id);
    }
    
    private void cadastrarMotorista() throws SQLException {
        System.out.println("\n=== CADASTRAR MOTORISTA ===");
        
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        
        System.out.print("CNH: ");
        String cnh = scanner.nextLine();
        
        System.out.print("Veículo: ");
        String veiculo = scanner.nextLine();
        
        System.out.print("Cidade Base: ");
        String cidadeBase = scanner.nextLine();
        
        Motorista motorista = new Motorista(nome, cnh, veiculo, cidadeBase);
        Long id = motoristaService.cadastrarMotorista(motorista);
        
        System.out.println("Motorista cadastrado com sucesso! ID: " + id);
    }
    
    private void criarPedido() throws SQLException {
        System.out.println("\n=== CRIAR PEDIDO ===");
        
        System.out.print("ID do Cliente: ");
        Long clienteId = Long.parseLong(scanner.nextLine());
        
        System.out.print("Volume (m³): ");
        BigDecimal volume = new BigDecimal(scanner.nextLine());
        
        System.out.print("Peso (kg): ");
        BigDecimal peso = new BigDecimal(scanner.nextLine());
        
        Pedido pedido = new Pedido(clienteId, volume, peso);
        Long id = pedidoService.criarPedido(pedido);
        
        System.out.println("Pedido criado com sucesso! ID: " + id);
    }
    
    private void atribuirPedidoAMotorista() throws SQLException {
        System.out.println("\n=== ATRIBUIR PEDIDO A MOTORISTA ===");
        
        System.out.print("ID do Pedido: ");
        Long pedidoId = Long.parseLong(scanner.nextLine());
        
        System.out.print("ID do Motorista: ");
        Long motoristaId = Long.parseLong(scanner.nextLine());
        
        Long entregaId = entregaService.atribuirPedidoAMotorista(pedidoId, motoristaId);
        
        System.out.println("Entrega criada com sucesso! ID: " + entregaId);
    }
    
    private void registrarEventoEntrega() throws SQLException {
        System.out.println("\n=== REGISTRAR EVENTO DE ENTREGA ===");
        
        System.out.print("ID da Entrega: ");
        Long entregaId = Long.parseLong(scanner.nextLine());
        
        System.out.print("Descrição do Evento: ");
        String descricao = scanner.nextLine();
        
        boolean sucesso = entregaService.registrarEventoEntrega(entregaId, descricao);
        
        if (sucesso) {
            System.out.println("Evento registrado com sucesso!");
        } else {
            System.out.println("Erro ao registrar evento!");
        }
    }
    
    private void atualizarStatusEntrega() throws SQLException {
        System.out.println("\n=== ATUALIZAR STATUS DA ENTREGA ===");
        
        System.out.print("ID da Entrega: ");
        Long entregaId = Long.parseLong(scanner.nextLine());
        
        System.out.println("Status disponíveis:");
        System.out.println("1 - EM_ROTA");
        System.out.println("2 - ENTREGUE");
        System.out.println("3 - ATRASADA");
        System.out.print("Escolha o status: ");
        
        int statusOpcao = Integer.parseInt(scanner.nextLine());
        StatusEntrega status;
        
        switch (statusOpcao) {
            case 1:
                status = StatusEntrega.EM_ROTA;
                break;
            case 2:
                status = StatusEntrega.ENTREGUE;
                break;
            case 3:
                status = StatusEntrega.ATRASADA;
                break;
            default:
                System.out.println("Status inválido!");
                return;
        }
        
        boolean sucesso = entregaService.atualizarStatusEntrega(entregaId, status);
        
        if (sucesso) {
            System.out.println("Status atualizado com sucesso!");
        } else {
            System.out.println("Erro ao atualizar status!");
        }
    }
    
    private void listarTodasEntregas() throws SQLException {
        System.out.println("\n=== TODAS AS ENTREGAS ===");
        
        List<Entrega> entregas = entregaService.listarTodasEntregas();
        
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega encontrada.");
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        for (Entrega entrega : entregas) {
            System.out.println("ID: " + entrega.getId());
            System.out.println("Pedido ID: " + entrega.getPedidoId());
            System.out.println("Motorista ID: " + entrega.getMotoristaId());
            System.out.println("Data Saída: " + (entrega.getDataSaida() != null ? entrega.getDataSaida().format(formatter) : "N/A"));
            System.out.println("Data Entrega: " + (entrega.getDataEntrega() != null ? entrega.getDataEntrega().format(formatter) : "N/A"));
            System.out.println("Status: " + entrega.getStatus());
            System.out.println("---");
        }
    }
    
    private void relatorioTotalEntregasPorMotorista() throws SQLException {
        System.out.println("\n=== RELATÓRIO: TOTAL DE ENTREGAS POR MOTORISTA ===");
        
        List<Object[]> relatorio = entregaService.relatorioTotalEntregasPorMotorista();
        
        if (relatorio.isEmpty()) {
            System.out.println("Nenhum dado encontrado.");
            return;
        }
        
        System.out.printf("%-30s %s%n", "Motorista", "Total Entregas");
        System.out.println("----------------------------------------");
        
        for (Object[] linha : relatorio) {
            System.out.printf("%-30s %d%n", linha[0], linha[1]);
        }
    }
    
    private void relatorioClientesMaiorVolume() throws SQLException {
        System.out.println("\n=== RELATÓRIO: CLIENTES COM MAIOR VOLUME ENTREGUE ===");
        
        List<Object[]> relatorio = entregaService.relatorioClientesMaiorVolume();
        
        if (relatorio.isEmpty()) {
            System.out.println("Nenhum dado encontrado.");
            return;
        }
        
        System.out.printf("%-30s %s%n", "Cliente", "Volume Total (m³)");
        System.out.println("----------------------------------------");
        
        for (Object[] linha : relatorio) {
            System.out.printf("%-30s %.3f%n", linha[0], linha[1]);
        }
    }
    
    private void relatorioPedidosPendentesPorEstado() throws SQLException {
        System.out.println("\n=== RELATÓRIO: PEDIDOS PENDENTES POR ESTADO ===");
        
        System.out.print("Estado (UF): ");
        String estado = scanner.nextLine();
        
        List<Pedido> pedidos = pedidoService.listarPedidosPendentesPorEstado(estado);
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido pendente encontrado para o estado " + estado);
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("Pedidos Pendentes no Estado " + estado + ":");
        System.out.println("----------------------------------------");
        
        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente ID: " + pedido.getClienteId());
            System.out.println("Data Pedido: " + pedido.getDataPedido().format(formatter));
            System.out.println("Volume: " + pedido.getVolumeM3() + " m³");
            System.out.println("Peso: " + pedido.getPesoKg() + " kg");
            System.out.println("---");
        }
    }
    
    private void relatorioEntregasAtrasadasPorCidade() throws SQLException {
        System.out.println("\n=== RELATÓRIO: ENTREGAS ATRASADAS POR CIDADE ===");
        
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        
        List<Entrega> entregas = entregaService.listarEntregasAtrasadasPorCidade(cidade);
        
        if (entregas.isEmpty()) {
            System.out.println("Nenhuma entrega atrasada encontrada para a cidade " + cidade);
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("Entregas Atrasadas na Cidade " + cidade + ":");
        System.out.println("----------------------------------------");
        
        for (Entrega entrega : entregas) {
            System.out.println("ID: " + entrega.getId());
            System.out.println("Pedido ID: " + entrega.getPedidoId());
            System.out.println("Motorista ID: " + entrega.getMotoristaId());
            System.out.println("Data Saída: " + (entrega.getDataSaida() != null ? entrega.getDataSaida().format(formatter) : "N/A"));
            System.out.println("Status: " + entrega.getStatus());
            System.out.println("---");
        }
    }
    
    private void buscarPedidoPorCpfCnpj() throws SQLException {
        System.out.println("\n=== BUSCAR PEDIDO POR CPF/CNPJ ===");
        
        System.out.print("CPF/CNPJ do Cliente: ");
        String cpfCnpj = scanner.nextLine();
        
        List<Pedido> pedidos = pedidoService.buscarPedidosPorCpfCnpjCliente(cpfCnpj);
        
        if (pedidos.isEmpty()) {
            System.out.println("Nenhum pedido encontrado para o CPF/CNPJ " + cpfCnpj);
            return;
        }
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        System.out.println("Pedidos do Cliente " + cpfCnpj + ":");
        System.out.println("----------------------------------------");
        
        for (Pedido pedido : pedidos) {
            System.out.println("ID: " + pedido.getId());
            System.out.println("Cliente ID: " + pedido.getClienteId());
            System.out.println("Data Pedido: " + pedido.getDataPedido().format(formatter));
            System.out.println("Volume: " + pedido.getVolumeM3() + " m³");
            System.out.println("Peso: " + pedido.getPesoKg() + " kg");
            System.out.println("Status: " + pedido.getStatus());
            System.out.println("---");
        }
    }
    
    private void cancelarPedido() throws SQLException {
        System.out.println("\n=== CANCELAR PEDIDO ===");
        
        System.out.print("ID do Pedido: ");
        Long pedidoId = Long.parseLong(scanner.nextLine());
        
        boolean sucesso = pedidoService.cancelarPedido(pedidoId);
        
        if (sucesso) {
            System.out.println("Pedido cancelado com sucesso!");
        } else {
            System.out.println("Erro ao cancelar pedido!");
        }
    }
    
    private void excluirEntrega() throws SQLException {
        System.out.println("\n=== EXCLUIR ENTREGA ===");
        
        System.out.print("ID da Entrega: ");
        Long entregaId = Long.parseLong(scanner.nextLine());
        
        boolean sucesso = entregaService.excluirEntrega(entregaId);
        
        if (sucesso) {
            System.out.println("Entrega excluída com sucesso!");
        } else {
            System.out.println("Erro ao excluir entrega!");
        }
    }
    
    private void excluirCliente() throws SQLException {
        System.out.println("\n=== EXCLUIR CLIENTE ===");
        
        System.out.print("ID do Cliente: ");
        Long clienteId = Long.parseLong(scanner.nextLine());
        
        boolean sucesso = clienteService.excluirCliente(clienteId);
        
        if (sucesso) {
            System.out.println("Cliente excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir cliente!");
        }
    }
    
    private void excluirMotorista() throws SQLException {
        System.out.println("\n=== EXCLUIR MOTORISTA ===");
        
        System.out.print("ID do Motorista: ");
        Long motoristaId = Long.parseLong(scanner.nextLine());
        
        boolean sucesso = motoristaService.excluirMotorista(motoristaId);
        
        if (sucesso) {
            System.out.println("Motorista excluído com sucesso!");
        } else {
            System.out.println("Erro ao excluir motorista!");
        }
    }
}
