package org.example.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.config.Conexao;

public class LivrosDAO {

    public boolean create(String titulo, String autor, int anoPublicacao) {
        String sql = "INSERT INTO livros (titulo, autor, ano_publicacao) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, anoPublicacao);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void read() {
        String sql = "SELECT * FROM livros";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nLista de Livros:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Título: " + rs.getString("titulo"));
                System.out.println("Autor: " + rs.getString("autor"));
                System.out.println("Ano: " + rs.getInt("ano_publicacao"));
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(int id, String titulo, String autor, int anoPublicacao) {
        String sql = "UPDATE livros SET titulo = ?, autor = ?, ano_publicacao = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            stmt.setString(2, autor);
            stmt.setInt(3, anoPublicacao);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Livro atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum livro encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM livros WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Livro deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum livro encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean atualizarAutor(String titulo, String novoAutor) {
        String sql = "UPDATE livros SET autor = ? WHERE titulo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoAutor);
            stmt.setString(2, titulo);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Autor atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum livro encontrado com o título especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletarLivro(String titulo) {
        String sql = "DELETE FROM livros WHERE titulo = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, titulo);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Livro deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum livro encontrado com o título especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
