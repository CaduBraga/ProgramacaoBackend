package com.logistica.service;

import com.logistica.dao.PedidoDAO;
import com.logistica.model.Pedido;
import com.logistica.model.StatusPedido;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class PedidoService {
    
    private PedidoDAO pedidoDAO;
    private ClienteService clienteService;
    
    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
        this.clienteService = new ClienteService();
    }
    
    public Long criarPedido(Pedido pedido) throws SQLException {
        if (pedido.getClienteId() == null) {
            throw new IllegalArgumentException("ID do cliente é obrigatório");
        }
        if (pedido.getVolumeM3() == null || pedido.getVolumeM3().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Volume deve ser maior que zero");
        }
        if (pedido.getPesoKg() == null || pedido.getPesoKg().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Peso deve ser maior que zero");
        }
        
        Cliente cliente = clienteService.buscarClientePorId(pedido.getClienteId());
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não encontrado");
        }
        
        return pedidoDAO.inserir(pedido);
    }
    
    public Pedido buscarPedidoPorId(Long id) throws SQLException {
        return pedidoDAO.buscarPorId(id);
    }
    
    public List<Pedido> listarTodosPedidos() throws SQLException {
        return pedidoDAO.listarTodos();
    }
    
    public List<Pedido> listarPedidosPorStatus(StatusPedido status) throws SQLException {
        return pedidoDAO.listarPorStatus(status);
    }
    
    public List<Pedido> listarPedidosPendentesPorEstado(String estado) throws SQLException {
        return pedidoDAO.listarPendentesPorEstado(estado);
    }
    
    public List<Pedido> buscarPedidosPorCpfCnpjCliente(String cpfCnpj) throws SQLException {
        return pedidoDAO.buscarPorCpfCnpjCliente(cpfCnpj);
    }
    
    public boolean cancelarPedido(Long id) throws SQLException {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        
        if (pedido.getStatus() != StatusPedido.PENDENTE) {
            throw new IllegalArgumentException("Apenas pedidos pendentes podem ser cancelados");
        }
        
        return pedidoDAO.cancelarPedido(id);
    }
    
    public boolean marcarPedidoComoEntregue(Long id) throws SQLException {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        
        return pedidoDAO.marcarComoEntregue(id);
    }
    
    public boolean excluirPedido(Long id) throws SQLException {
        Pedido pedido = pedidoDAO.buscarPorId(id);
        if (pedido == null) {
            throw new IllegalArgumentException("Pedido não encontrado");
        }
        
        return pedidoDAO.excluir(id);
    }
}
