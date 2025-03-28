package dao;

import conexaoDB.DBConnection;
import model.Categoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void inserirCategoria(Categoria categoria) {
        String sql = "{CALL inserirCategoria(?, ?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, categoria.getNome());
            stmt.setString(2, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir categoria: " + e.getMessage(), e);
        }
   
    }

    public void atualizarCategoria(Categoria categoria) {
        String sql = "{CALL atualizarCategoria(?, ?, ?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, categoria.getId());
            stmt.setString(2, categoria.getNome());
            stmt.setString(3, categoria.getDescricao());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar categoria: " + e.getMessage(), e);
        }
        // Não fechamos a conexão aqui, pois queremos mantê-la aberta
    }

    public void excluirCategoria(int id) {
        String sql = "{CALL excluirCategoria(?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir categoria: " + e.getMessage(), e);
        }
       
    }

    public List<Categoria> listarCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT * FROM Categoria";  // Consulta simples
        Connection conn = DBConnection.getConnection(); // Obtém a conexão

        try (Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Categoria categoria = new Categoria(
                        rs.getInt("IDcategoria"),
                        rs.getString("nomeCategoria"),
                        rs.getString("descricaoCategoria")
                );
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar categorias: " + e.getMessage(), e);
        }
       
        return categorias;
    }
}
