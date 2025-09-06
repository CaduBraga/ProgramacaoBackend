package com.lista.dao;

import com.lista.model.Contato;
import com.lista.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {

    public int salvar(Contato contato) throws SQLException {
        String sql = "INSERT INTO contato (nome, telefone, email, observacao) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.setString(4, contato.getObservacao());
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        return generatedKeys.getInt(1);
                    }
                }
            }
            return 0;
        }
    }

    public List<Contato> listar() throws SQLException {
        String sql = "SELECT * FROM contato ORDER BY nome";
        List<Contato> contatos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            
            while (rs.next()) {
                contatos.add(criarContatoDoResultSet(rs));
            }
        }
        return contatos;
    }

    public Contato buscarPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM contato WHERE nome LIKE ? ORDER BY nome";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarContatoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    public List<Contato> buscarTodosPorNome(String nome) throws SQLException {
        String sql = "SELECT * FROM contato WHERE nome LIKE ? ORDER BY nome";
        List<Contato> contatos = new ArrayList<>();
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + nome + "%");
            
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    contatos.add(criarContatoDoResultSet(rs));
                }
            }
        }
        return contatos;
    }

    public boolean atualizar(Contato contato) throws SQLException {
        String sql = "UPDATE contato SET telefone = ?, email = ?, observacao = ? WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, contato.getTelefone());
            stmt.setString(2, contato.getEmail());
            stmt.setString(3, contato.getObservacao());
            stmt.setInt(4, contato.getId());
            
            return stmt.executeUpdate() > 0;
        }
    }

    public boolean deletar(int id) throws SQLException {
        String sql = "DELETE FROM contato WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        }
    }

    public Contato buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM contato WHERE id = ?";
        
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, id);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return criarContatoDoResultSet(rs);
                }
            }
        }
        return null;
    }

    private Contato criarContatoDoResultSet(ResultSet rs) throws SQLException {
        Contato contato = new Contato();
        contato.setId(rs.getInt("id"));
        contato.setNome(rs.getString("nome"));
        contato.setTelefone(rs.getString("telefone"));
        contato.setEmail(rs.getString("email"));
        contato.setObservacao(rs.getString("observacao"));
        return contato;
    }
}
