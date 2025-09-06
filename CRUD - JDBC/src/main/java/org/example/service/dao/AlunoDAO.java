package org.example.service.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.example.config.Conexao;

public class AlunoDAO {

    public boolean create(String nome, String matricula, String curso) {
        String sql = "INSERT INTO alunos (nome, matricula, curso) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, matricula);
            stmt.setString(3, curso);
            stmt.executeUpdate();

            System.out.println("Aluno inserido com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void read() {
        String sql = "SELECT * FROM alunos";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Matrícula: %s | Curso: %s\n",
                        rs.getInt("id"), rs.getString("nome"), rs.getString("matricula"), rs.getString("curso"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(int id, String nome, String matricula, String curso) {
        String sql = "UPDATE alunos SET nome = ?, matricula = ?, curso = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setString(2, matricula);
            stmt.setString(3, curso);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum aluno encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM alunos WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum aluno encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean atualizarCurso(String matricula, String novoCurso) {
        String sql = "UPDATE alunos SET curso = ? WHERE matricula = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, novoCurso);
            stmt.setString(2, matricula);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Curso atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum aluno encontrado com a matrícula especificada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deletarAluno(String matricula) {
        String sql = "DELETE FROM alunos WHERE matricula = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, matricula);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Aluno deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum aluno encontrado com a matrícula especificada.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}