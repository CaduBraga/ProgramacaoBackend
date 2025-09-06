package com.logistica.service;

import com.logistica.dao.EntregaDAO;
import com.logistica.dao.HistoricoEntregaDAO;
import com.logistica.model.Entrega;
import com.logistica.model.HistoricoEntrega;
import com.logistica.model.StatusEntrega;
import com.logistica.model.Pedido;
import com.logistica.model.Motorista;

import java.sql.SQLException;
import java.util.List;

public class EntregaService {
    
    private EntregaDAO entregaDAO;
    private HistoricoEntregaDAO historicoDAO;
    private PedidoService pedidoService;
    private MotoristaService motoristaService;
    
    public EntregaService() {
        this.entregaDAO = new EntregaDAO();
        this.historicoDAO = new HistoricoEntregaDAO();
        this.pedidoService = new PedidoService();
        this.motoristaService = new MotoristaService();
    }
    
    public Long atribuirPedidoAMotorista(Long pedidoId, Long motoristaId) throws SQLException {
        if (pedidoId == null) {
            throw new IllegalArgumentException("ID do pedido é obrigatório");
        }
        if (motoristaId == null) {
            throw new IllegalArgumentException("ID do motorista é obrigatório");
        }
        
        Pedido pedido = pedidoService.buscarPedidoPorId(pedidoId);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        
        if (pedido.getStatus() != com.logistica.model.StatusPedido.PENDENTE) {
            throw new IllegalArgumentException("Apenas pedidos pendentes podem ser atribuídos a motoristas");
        }
        
        Motorista motorista = motoristaService.buscarMotoristaPorId(motoristaId);
        if (motorista == null) {
            throw new IllegalArgumentException("Motorista não encontrado");
        }
        
        Entrega entrega = new Entrega(pedidoId, motoristaId);
        Long entregaId = entregaDAO.inserir(entrega);
        
        if (entregaId != null) {
            registrarEventoEntrega(entregaId, "Entrega iniciada - saída do depósito");
        }
        
        return entregaId;
    }
    
    public boolean registrarEventoEntrega(Long entregaId, String descricao) throws SQLException {
        if (entregaId == null) {
            throw new IllegalArgumentException("ID da entrega é obrigatório");
        }
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do evento é obrigatória");
        }
        
        Entrega entrega = entregaDAO.buscarPorId(entregaId);
        if (entrega == null) {
            throw new IllegalArgumentException("Entrega não encontrada");
        }
        
        HistoricoEntrega historico = new HistoricoEntrega(entregaId, descricao);
        return historicoDAO.inserir(historico) != null;
    }
    
    public boolean atualizarStatusEntrega(Long entregaId, StatusEntrega status) throws SQLException {
        if (entregaId == null) {
            throw new IllegalArgumentException("ID da entrega é obrigatório");
        }
        if (status == null) {
            throw new IllegalArgumentException("Status é obrigatório");
        }
        
        Entrega entrega = entregaDAO.buscarPorId(entregaId);
        if (entrega == null) {
            throw new IllegalArgumentException("Entrega não encontrada");
        }
        
        boolean sucesso = entregaDAO.atualizarStatus(entregaId, status);
        
        if (sucesso) {
            String descricao = "Status alterado para: " + status.getDescricao();
            registrarEventoEntrega(entregaId, descricao);
            
            if (status == StatusEntrega.ENTREGUE) {
                entregaDAO.marcarComoEntregue(entregaId);
                pedidoService.marcarPedidoComoEntregue(entrega.getPedidoId());
            }
        }
        
        return sucesso;
    }
    
    public List<Entrega> listarTodasEntregas() throws SQLException {
        return entregaDAO.listarTodas();
    }
    
    public List<Entrega> listarEntregasPorStatus(StatusEntrega status) throws SQLException {
        return entregaDAO.listarPorStatus(status);
    }
    
    public List<Entrega> listarEntregasAtrasadasPorCidade(String cidade) throws SQLException {
        return entregaDAO.listarAtrasadasPorCidade(cidade);
    }
    
    public List<HistoricoEntrega> listarHistoricoEntrega(Long entregaId) throws SQLException {
        return historicoDAO.listarPorEntrega(entregaId);
    }
    
    public boolean excluirEntrega(Long id) throws SQLException {
        Entrega entrega = entregaDAO.buscarPorId(id);
        if (entrega == null) {
            throw new IllegalArgumentException("Entrega não encontrada");
        }
        
        return entregaDAO.excluir(id);
    }
    
    public List<Object[]> relatorioTotalEntregasPorMotorista() throws SQLException {
        return entregaDAO.relatorioTotalEntregasPorMotorista();
    }
    
    public List<Object[]> relatorioClientesMaiorVolume() throws SQLException {
        return entregaDAO.relatorioClientesMaiorVolume();
    }
}
