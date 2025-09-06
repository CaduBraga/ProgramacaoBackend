package com.logistica.dao;

import com.logistica.config.DatabaseConfig;
import com.logistica.model.HistoricoEntrega;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoricoEntregaDAO {

    public Long inserir(HistoricoEntrega historico) throws SQLException {
        String sql = "INSERT INTO historico_entregas (entrega_id, data_evento, descricao) VALUES (?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setLong(1, historico.getEntregaId());
            stmt.setTimestamp(2, Timestamp.valueOf(historico.getDataEvento()));
            stmt.setString(3, historico.getDescricao());
            
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

    public List<HistoricoEntrega> listarPorEntrega(Long entregaId) throws SQLException {
        String sql = "SELECT * FROM historico_entregas WHERE entrega_id = ? ORDER BY data_evento ASC";
        List<HistoricoEntrega> historicos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, entregaId);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    historicos.add(criarHistoricoDoResultSet(rs));
                }
            }
        }
        return historicos;
    }

    public List<HistoricoEntrega> listarTodos() throws SQLException {
        String sql = "SELECT h.*, e.pedido_id, e.motorista_id " +
                    "FROM historico_entregas h " +
                    "LEFT JOIN entregas e ON h.entrega_id = e.id " +
                    "ORDER BY h.data_evento DESC";
        List<HistoricoEntrega> historicos = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                historicos.add(criarHistoricoDoResultSet(rs));
            }
        }
        return historicos;
    }

    public boolean excluir(Long id) throws SQLException {
        String sql = "DELETE FROM historico_entregas WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    private HistoricoEntrega criarHistoricoDoResultSet(ResultSet rs) throws SQLException {
        HistoricoEntrega historico = new HistoricoEntrega();
        historico.setId(rs.getLong("id"));
        historico.setEntregaId(rs.getLong("entrega_id"));
        historico.setDescricao(rs.getString("descricao"));
        
        Timestamp timestamp = rs.getTimestamp("data_evento");
        if (timestamp != null) {
            historico.setDataEvento(timestamp.toLocalDateTime());
        }
        
        return historico;
    }
}
