package com.logistica.dao;

import com.logistica.config.DatabaseConfig;
import com.logistica.model.Entrega;
import com.logistica.model.StatusEntrega;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EntregaDAO {

    public Long inserir(Entrega entrega) throws SQLException {
        String sql = "INSERT INTO entregas (pedido_id, motorista_id, data_saida, status) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, entrega.getPedidoId());
            stmt.setLong(2, entrega.getMotoristaId());
            stmt.setTimestamp(3, Timestamp.valueOf(entrega.getDataSaida()));
            stmt.setString(4, entrega.getStatus().name());
            
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

    public Entrega buscarPorId(Long id) throws SQLException {
        String sql = "SELECT e.*, p.volume_m3, p.peso_kg, p.status as pedido_status, " +
                    "c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado, " +
                    "m.nome as motorista_nome, m.cnh, m.veiculo, m.cidade_base " +
                    "FROM entregas e " +
                    "LEFT JOIN pedidos p ON e.pedido_id = p.id " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "LEFT JOIN motoristas m ON e.motorista_id = m.id " +
                    "WHERE e.id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarEntregaDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Entrega> listarTodas() throws SQLException {
        String sql = "SELECT e.*, p.volume_m3, p.peso_kg, p.status as pedido_status, " +
                    "c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado, " +
                    "m.nome as motorista_nome, m.cnh, m.veiculo, m.cidade_base " +
                    "FROM entregas e " +
                    "LEFT JOIN pedidos p ON e.pedido_id = p.id " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "LEFT JOIN motoristas m ON e.motorista_id = m.id " +
                    "ORDER BY e.data_saida DESC";
        List<Entrega> entregas = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                entregas.add(criarEntregaDoResultSet(rs));
            }
        }
        return entregas;
    }

    public List<Entrega> listarPorStatus(StatusEntrega status) throws SQLException {
        String sql = "SELECT e.*, p.volume_m3, p.peso_kg, p.status as pedido_status, " +
                    "c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado, " +
                    "m.nome as motorista_nome, m.cnh, m.veiculo, m.cidade_base " +
                    "FROM entregas e " +
                    "LEFT JOIN pedidos p ON e.pedido_id = p.id " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "LEFT JOIN motoristas m ON e.motorista_id = m.id " +
                    "WHERE e.status = ? " +
                    "ORDER BY e.data_saida DESC";
        List<Entrega> entregas = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entregas.add(criarEntregaDoResultSet(rs));
                }
            }
        }
        return entregas;
    }

    public List<Entrega> listarAtrasadasPorCidade(String cidade) throws SQLException {
        String sql = "SELECT e.*, p.volume_m3, p.peso_kg, p.status as pedido_status, " +
                    "c.nome as cliente_nome, c.cpf_cnpj, c.cidade, c.estado, " +
                    "m.nome as motorista_nome, m.cnh, m.veiculo, m.cidade_base " +
                    "FROM entregas e " +
                    "LEFT JOIN pedidos p ON e.pedido_id = p.id " +
                    "LEFT JOIN clientes c ON p.cliente_id = c.id " +
                    "LEFT JOIN motoristas m ON e.motorista_id = m.id " +
                    "WHERE e.status = 'ATRASADA' AND c.cidade = ? " +
                    "ORDER BY e.data_saida ASC";
        List<Entrega> entregas = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cidade);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    entregas.add(criarEntregaDoResultSet(rs));
                }
            }
        }
        return entregas;
    }

    public boolean atualizarStatus(Long id, StatusEntrega status) throws SQLException {
        String sql = "UPDATE entregas SET status = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, status.name());
            stmt.setLong(2, id);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean marcarComoEntregue(Long id) throws SQLException {
        String sql = "UPDATE entregas SET status = 'ENTREGUE', data_entrega = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            stmt.setLong(2, id);
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(Long id) throws SQLException {
        String sql = "DELETE FROM entregas WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public List<Object[]> relatorioTotalEntregasPorMotorista() throws SQLException {
        String sql = "SELECT m.nome as motorista_nome, COUNT(e.id) as total_entregas " +
                    "FROM motoristas m " +
                    "LEFT JOIN entregas e ON m.id = e.motorista_id " +
                    "GROUP BY m.id, m.nome " +
                    "ORDER BY total_entregas DESC";
        List<Object[]> relatorio = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] linha = {
                    rs.getString("motorista_nome"),
                    rs.getLong("total_entregas")
                };
                relatorio.add(linha);
            }
        }
        return relatorio;
    }

    public List<Object[]> relatorioClientesMaiorVolume() throws SQLException {
        String sql = "SELECT c.nome as cliente_nome, SUM(p.volume_m3) as total_volume " +
                    "FROM clientes c " +
                    "LEFT JOIN pedidos p ON c.id = p.cliente_id " +
                    "LEFT JOIN entregas e ON p.id = e.pedido_id " +
                    "WHERE e.status = 'ENTREGUE' " +
                    "GROUP BY c.id, c.nome " +
                    "ORDER BY total_volume DESC " +
                    "LIMIT 10";
        List<Object[]> relatorio = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                Object[] linha = {
                    rs.getString("cliente_nome"),
                    rs.getBigDecimal("total_volume")
                };
                relatorio.add(linha);
            }
        }
        return relatorio;
    }

    private Entrega criarEntregaDoResultSet(ResultSet rs) throws SQLException {
        Entrega entrega = new Entrega();
        entrega.setId(rs.getLong("id"));
        entrega.setPedidoId(rs.getLong("pedido_id"));
        entrega.setMotoristaId(rs.getLong("motorista_id"));
        entrega.setStatus(StatusEntrega.valueOf(rs.getString("status")));
        
        Timestamp timestamp = rs.getTimestamp("data_saida");
        if (timestamp != null) {
            entrega.setDataSaida(timestamp.toLocalDateTime());
        }
        
        timestamp = rs.getTimestamp("data_entrega");
        if (timestamp != null) {
            entrega.setDataEntrega(timestamp.toLocalDateTime());
        }
        
        return entrega;
    }
}
