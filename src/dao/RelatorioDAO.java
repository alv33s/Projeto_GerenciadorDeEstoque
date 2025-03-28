package dao;

import conexaoDB.DBConnection;

import java.sql.*;

public class RelatorioDAO {

    // Relatório de produtos
    public String obterRelatorioProdutos() {
        StringBuilder relatorio = new StringBuilder("Relatório de Produtos:\n");
        String sql = "SELECT * FROM Produto";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Obtém a conexão com o banco
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                relatorio.append("ID: ").append(rs.getInt("IDproduto"))
                         .append(", Nome: ").append(rs.getString("nomeProduto"))
                         .append(", Estoque: ").append(rs.getInt("qttdEstoque"))
                         .append(", Preço Compra: ").append(rs.getDouble("precoDeCompra"))
                         .append(", Preço Venda: ").append(rs.getDouble("precoDeVenda"))
                         .append("\n");
            }
        } catch (SQLException e) {
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        } finally {
            // Não fechamos a conexão aqui, pois queremos mantê-la aberta
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                relatorio.append("Erro ao fechar recursos: ").append(e.getMessage());
            }
        }

        return relatorio.toString();
    }

    // Relatório de movimentações de estoque
    public String obterRelatorioMovimentacoes() {
        StringBuilder relatorio = new StringBuilder("Relatório de Movimentações:\n");
        String sql = "SELECT * FROM MovimentacaoEstoque";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Obtém a conexão com o banco
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                relatorio.append("ID: ").append(rs.getInt("IDmovimentacao"))
                         .append(", Produto: ").append(rs.getInt("IDproduto"))
                         .append(", Tipo: ").append(rs.getString("tipoMovimentacao"))
                         .append(", Quantidade: ").append(rs.getInt("quantidade"))
                         .append(", Data: ").append(rs.getTimestamp("dataMovimentacao"))
                         .append("\n");
            }
        } catch (SQLException e) {
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        } finally {
            // Não fechamos a conexão aqui, pois queremos mantê-la aberta
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                relatorio.append("Erro ao fechar recursos: ").append(e.getMessage());
            }
        }

        return relatorio.toString();
    }

    // Relatório de produtos com baixo estoque
    public String obterRelatorioBaixoEstoque() {
        StringBuilder relatorio = new StringBuilder("Relatório de Produtos com Baixo Estoque:\n");
        String sql = "SELECT * FROM Produto WHERE qttdEstoque < 10";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Obtém a conexão com o banco
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                relatorio.append("ID: ").append(rs.getInt("IDproduto"))
                         .append(", Nome: ").append(rs.getString("nomeProduto"))
                         .append(", Estoque: ").append(rs.getInt("qttdEstoque"))
                         .append("\n");
            }
        } catch (SQLException e) {
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        } finally {
            // Não fechamos a conexão aqui, pois queremos mantê-la aberta
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                relatorio.append("Erro ao fechar recursos: ").append(e.getMessage());
            }
        }

        return relatorio.toString();
    }

    // Relatório de lucro (diferencial entre preço de venda e de compra)
    public String obterRelatorioLucro() {
        StringBuilder relatorio = new StringBuilder("Relatório de Lucro:\n");
        String sql = "SELECT SUM((precoDeVenda - precoDeCompra) * qttdEstoque) AS lucro FROM Produto";
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection(); // Obtém a conexão com o banco
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            if (rs.next()) {
                relatorio.append("Lucro Total: ").append(rs.getDouble("lucro")).append("\n");
            }
        } catch (SQLException e) {
            relatorio.append("Erro ao gerar relatório: ").append(e.getMessage());
        } finally {
            // Não fechamos a conexão aqui, pois queremos mantê-la aberta
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                relatorio.append("Erro ao fechar recursos: ").append(e.getMessage());
            }
        }

        return relatorio.toString();
    }
}
