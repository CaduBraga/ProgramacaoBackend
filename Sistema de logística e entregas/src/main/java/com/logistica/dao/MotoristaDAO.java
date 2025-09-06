package com.logistica.dao;

import com.logistica.config.DatabaseConfig;
import com.logistica.model.Motorista;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MotoristaDAO {

    public Long inserir(Motorista motorista) throws SQLException {
        String sql = "INSERT INTO motoristas (nome, cnh, veiculo, cidade_base, data_cadastro) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidadeBase());
            stmt.setTimestamp(5, Timestamp.valueOf(motorista.getDataCadastro()));
            
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

    public Motorista buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarMotoristaDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public Motorista buscarPorCnh(String cnh) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE cnh = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cnh);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarMotoristaDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Motorista> listarTodos() throws SQLException {
        String sql = "SELECT * FROM motoristas ORDER BY nome";
        List<Motorista> motoristas = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                motoristas.add(criarMotoristaDoResultSet(rs));
            }
        }
        return motoristas;
    }

    public List<Motorista> listarPorCidadeBase(String cidadeBase) throws SQLException {
        String sql = "SELECT * FROM motoristas WHERE cidade_base = ? ORDER BY nome";
        List<Motorista> motoristas = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cidadeBase);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    motoristas.add(criarMotoristaDoResultSet(rs));
                }
            }
        }
        return motoristas;
    }

    public boolean atualizar(Motorista motorista) throws SQLException {
        String sql = "UPDATE motoristas SET nome = ?, cnh = ?, veiculo = ?, cidade_base = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, motorista.getNome());
            stmt.setString(2, motorista.getCnh());
            stmt.setString(3, motorista.getVeiculo());
            stmt.setString(4, motorista.getCidadeBase());
            stmt.setLong(5, motorista.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(Long id) throws SQLException {
        String sqlVerificacao = "SELECT COUNT(*) FROM entregas WHERE motorista_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlVerificacao)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
        }
        
        String sql = "DELETE FROM motoristas WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean temEntregasAssociadas(Long id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM entregas WHERE motorista_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private Motorista criarMotoristaDoResultSet(ResultSet rs) throws SQLException {
        Motorista motorista = new Motorista();
        motorista.setId(rs.getLong("id"));
        motorista.setNome(rs.getString("nome"));
        motorista.setCnh(rs.getString("cnh"));
        motorista.setVeiculo(rs.getString("veiculo"));
        motorista.setCidadeBase(rs.getString("cidade_base"));
        
        Timestamp timestamp = rs.getTimestamp("data_cadastro");
        if (timestamp != null) {
            motorista.setDataCadastro(timestamp.toLocalDateTime());
        }
        
        return motorista;
    }
}
