package org.example.service.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.example.config.Conexao;

public class PedidosDAO {

    public boolean create(String cliente, Date dataPedido, double total) {
        String sql = "INSERT INTO pedidos (cliente, data_pedido, total) VALUES (?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente);
            stmt.setDate(2, dataPedido);
            stmt.setDouble(3, total);
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void read() {
        String sql = "SELECT * FROM pedidos";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nLista de Pedidos:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Cliente: " + rs.getString("cliente"));
                System.out.println("Data: " + rs.getDate("data_pedido"));
                System.out.println("Total: " + rs.getDouble("total"));
                System.out.println("--------------------------");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean update(int id, String cliente, Date dataPedido, double total) {
        String sql = "UPDATE pedidos SET cliente = ?, data_pedido = ?, total = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente);
            stmt.setDate(2, dataPedido);
            stmt.setDouble(3, total);
            stmt.setInt(4, id);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM pedidos WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Pedido deletado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean atualizarValorTotal(int id, double novoTotal) {
        String sql = "UPDATE pedidos SET total = ? WHERE id = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setDouble(1, novoTotal);
            stmt.setInt(2, id);
            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Valor total atualizado com sucesso!");
                return true;
            } else {
                System.out.println("Nenhum pedido encontrado com o ID especificado.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}