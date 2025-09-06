package com.logistica.dao;

import com.logistica.config.DatabaseConfig;
import com.logistica.model.Cliente;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {

    public Long inserir(Cliente cliente) throws SQLException {
        String sql = "INSERT INTO clientes (nome, cpf_cnpj, endereco, cidade, estado, data_cadastro) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.setTimestamp(6, Timestamp.valueOf(cliente.getDataCadastro()));
            
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

    public Cliente buscarPorId(Long id) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarClienteDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public Cliente buscarPorCpfCnpj(String cpfCnpj) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE cpf_cnpj = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cpfCnpj);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarClienteDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Cliente> listarTodos() throws SQLException {
        String sql = "SELECT * FROM clientes ORDER BY nome";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                clientes.add(criarClienteDoResultSet(rs));
            }
        }
        return clientes;
    }

    public List<Cliente> listarPorEstado(String estado) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE estado = ? ORDER BY nome";
        List<Cliente> clientes = new ArrayList<>();
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, estado);
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    clientes.add(criarClienteDoResultSet(rs));
                }
            }
        }
        return clientes;
    }

    public boolean atualizar(Cliente cliente) throws SQLException {
        String sql = "UPDATE clientes SET nome = ?, cpf_cnpj = ?, endereco = ?, cidade = ?, estado = ? WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpfCnpj());
            stmt.setString(3, cliente.getEndereco());
            stmt.setString(4, cliente.getCidade());
            stmt.setString(5, cliente.getEstado());
            stmt.setLong(6, cliente.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean excluir(Long id) throws SQLException {
        String sqlVerificacao = "SELECT COUNT(*) FROM pedidos WHERE cliente_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sqlVerificacao)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next() && rs.getInt(1) > 0) {
                    return false;
                }
            }
        }
        
        String sql = "DELETE FROM clientes WHERE id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean temPedidosAssociados(Long id) throws SQLException {
        String sql = "SELECT COUNT(*) FROM pedidos WHERE cliente_id = ?";
        
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setLong(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() && rs.getInt(1) > 0;
            }
        }
    }

    private Cliente criarClienteDoResultSet(ResultSet rs) throws SQLException {
        Cliente cliente = new Cliente();
        cliente.setId(rs.getLong("id"));
        cliente.setNome(rs.getString("nome"));
        cliente.setCpfCnpj(rs.getString("cpf_cnpj"));
        cliente.setEndereco(rs.getString("endereco"));
        cliente.setCidade(rs.getString("cidade"));
        cliente.setEstado(rs.getString("estado"));
        
        Timestamp timestamp = rs.getTimestamp("data_cadastro");
        if (timestamp != null) {
            cliente.setDataCadastro(timestamp.toLocalDateTime());
        }
        
        return cliente;
    }
}
