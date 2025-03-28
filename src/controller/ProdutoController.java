package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaoDB.DBConnection;
import dao.ProdutoDAO;
import model.Produto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ProdutoController {
    private ProdutoDAO produtoDAO;
    private Connection conn;

    public ProdutoController() {
        produtoDAO = new ProdutoDAO();
        conn = DBConnection.getConnection();
    }

    public void cadastrarProduto(String nomeProduto, String descricao, int qttdEstoque, double precoDeCompra, double precoDeVenda, int idCategoria) throws SQLException {
        Produto produto = new Produto();
        produto.setNome(nomeProduto);
        produto.setDescricao(descricao);
        produto.setQuantidadeEstoque(qttdEstoque);
        produto.setPrecoCompra(precoDeCompra);
        produto.setPrecoVenda(precoDeVenda);
        produto.setCategoriaId(idCategoria);
        produtoDAO.inserirProduto(produto);
    }

    public void editarProduto(int idProduto, String nome, String descricao, int quantidade, double precoCompra, double precoVenda, int idCategoria) throws SQLException {
        String sql = "UPDATE Produto SET nomeProduto = ?, descricao = ?, qttdEstoque = ?, precoDeCompra = ?, precoDeVenda = ?, IDcategoria = ? WHERE IDproduto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, descricao);
            stmt.setInt(3, quantidade);
            stmt.setDouble(4, precoCompra);
            stmt.setDouble(5, precoVenda);
            stmt.setInt(6, idCategoria);
            stmt.setInt(7, idProduto);
            stmt.executeUpdate();
        }
    }

    public void excluirProduto(int idProduto) throws SQLException {
        String sql = "DELETE FROM Produto WHERE IDproduto = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, idProduto);
            stmt.executeUpdate();
        }
    }

    public List<Produto> consultarProdutos(String nome, int idCategoria, Integer quantidadeEstoque) throws SQLException {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM Produto WHERE 1=1";

        if (nome != null && !nome.isEmpty()) {
            sql += " AND nomeProduto LIKE ?";
        }
        if (idCategoria > 0) {
            sql += " AND IDcategoria = ?";
        }
        if (quantidadeEstoque != null) {
            sql += " AND qttdEstoque = ?";
        }

        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            int index = 1;
            if (nome != null && !nome.isEmpty()) {
                stmt.setString(index++, "%" + nome + "%");
            }
            if (idCategoria > 0) {
                stmt.setInt(index++, idCategoria);
            }
            if (quantidadeEstoque != null) {
                stmt.setInt(index++, quantidadeEstoque);
            }

            ResultSet rs = stmt.executeQuery();
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
        }
        return produtos;
    }



}
