package com.logistica.dao;

import com.logistica.config.DatabaseConfig;
import com.logistica.model.Pedido;
import com.logistica.model.StatusPedido;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public Long inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO pedidos (cliente_id, data_pedido, volume_m3, peso_kg, status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, pedido.getClienteId());
            stmt.setTimestamp(2, Timestamp.valueOf(pedido.getDataPedido()));
            stmt.setBigDecimal(3, pedido.getVolumeM3());
            stmt.setBigDecimal(4, pedido.getPesoKg());
            stmt.setString(5, pedido.getStatus().name());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getLong(1);
                    }
                }
            }
            return null;
        }
    }

    public Pedido buscarPorId(Long id) throws SQLException {
        String sql = "SELECT p.*, c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado " +
                    "FROM pedidos p " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "WHERE p.id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarPedidoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Pedido> listarTodos() throws SQLException {
        String sql = "SELECT p.*, c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado " +
                    "FROM pedidos p " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "ORDER BY p.data_pedido DESC";
        List<Pedido> pedidos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                pedidos.add(criarPedidoDoResultSet(rs));
            }
        }
        return pedidos;
    }

    public List<Pedido> listarPorStatus(StatusPedido status) throws SQLException {
        String sql = "SELECT p.*, c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado " +
                    "FROM pedidos p " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "WHERE p.status = ? " +
                    "ORDER BY p.data_pedido DESC";
        List<Pedido> pedidos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(criarPedidoDoResultSet(rs));
                }
            }
        }
        return pedidos;
    }

    public List<Pedido> listarPendentesPorEstado(String estado) throws SQLException {
        String sql = "SELECT p.*, c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado " +
                    "FROM pedidos p " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "WHERE p.status = 'PENDENTE' AND c.estado = ? " +
                    "ORDER BY p.data_pedido ASC";
        List<Pedido> pedidos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(criarPedidoDoResultSet(rs));
                }
            }
        }
        return pedidos;
    }

    public List<Pedido> buscarPorCpfCnpjCliente(String cpfCnpj) throws SQLException {
        String sql = "SELECT p.*, c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado " +
                    "FROM pedidos p " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "WHERE c.cpf_cnpj = ? " +
                    "ORDER BY p.data_pedido DESC";
        List<Pedido> pedidos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpfCnpj);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    pedidos.add(criarPedidoDoResultSet(rs));
                }
            }
        }
        return pedidos;
    }

    public boolean atualizarStatus(Long id, StatusPedido status) throws SQLException {
        String sql = "UPDATE pedidos SET status = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            stmt.setLong(2, id);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean cancelarPedido(Long id) throws SQLException {
        return atualizarStatus(id, StatusPedido.CANCELADO);
    }

    public boolean marcarComoEntregue(Long id) throws SQLException {
        return atualizarStatus(id, StatusPedido.ENTREGUE);
    }

    public boolean excluir(Long id) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private Pedido criarPedidoDoResultSet(ResultSet rs) throws SQLException {
        Pedido pedido = new Pedido();
        pedido.setId(rs.getLong("id"));
        pedido.setClienteId(rs.getLong("cliente_id"));
        pedido.setVolumeM3(rs.getBigDecimal("volume_m3"));
        pedido.setPesoKg(rs.getBigDecimal("peso_kg"));
        pedido.setStatus(StatusPedido.valueOf(rs.getString("status")));
        
        Timestamp timestamp = rs.getTimestamp("data_pedido");
        if (timestamp != null) {
            pedido.setDataPedido(timestamp.toLocalDateTime());
        }
        
        if (rs.getString("cliente_nome") != null) {
        }
        
        return pedido;
    }
}
