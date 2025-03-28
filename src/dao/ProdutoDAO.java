package dao;

import conexaoDB.DBConnection;
import model.Produto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public void inserirProduto(Produto produto) throws SQLException {
        String sql = "{CALL inserirProduto(?, ?, ?, ?, ?, ?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setString(1, produto.getNome());
            stmt.setString(2, produto.getDescricao());
            stmt.setInt(3, produto.getQuantidadeEstoque());
            stmt.setDouble(4, produto.getPrecoCompra());
            stmt.setDouble(5, produto.getPrecoVenda());
            stmt.setInt(6, produto.getCategoriaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao inserir produto: " + e.getMessage(), e);
        }
        
    }

    public void atualizarProduto(Produto produto) throws SQLException {
        String sql = "{CALL atualizarProduto(?, ?, ?, ?, ?, ?, ?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, produto.getId());
            stmt.setString(2, produto.getNome());
            stmt.setString(3, produto.getDescricao());
            stmt.setInt(4, produto.getQuantidadeEstoque());
            stmt.setDouble(5, produto.getPrecoCompra());
            stmt.setDouble(6, produto.getPrecoVenda());
            stmt.setInt(7, produto.getCategoriaId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao atualizar produto: " + e.getMessage(), e);
        }
     
    }

    public void excluirProduto(int idProduto) throws SQLException {
        String sql = "{CALL excluirProduto(?)}";  // Chama a stored procedure
        Connection conn = DBConnection.getConnection(); // Obtém a conexão
        try (CallableStatement stmt = conn.prepareCall(sql)) {
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao excluir produto: " + e.getMessage(), e);
        }
  
    }

    public List<Produto> consultarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto";  // Consulta simples
        Connection conn = DBConnection.getConnection(); // Obtém a conexão

        try (Statement stmt = conn.createStatement(); 
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Produto produto = new Produto(
                        rs.getInt("IDproduto"),
                        rs.getString("nomeProduto"),
                        rs.getString("descricao"),
                        rs.getInt("qttdEstoque"),
                        rs.getDouble("precoDeCompra"),
                        rs.getDouble("precoDeVenda"),
                        rs.getInt("IDcategoria")
                );
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar Produtos: " + e.getMessage(), e);
        }
       
        return produtos;
    }
}
