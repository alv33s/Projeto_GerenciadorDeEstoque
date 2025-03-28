package conexaoDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	 // Constantes de configuração para a conexão com o banco de dados
    private static final String URL = "jdbc:mysql://localhost:3306/gerenciadorDeEstoque?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "25097800";
    
    // Variável de instância para a conexão
    private static Connection conexao;

    private DBConnection() {
        // Previne a criação de instâncias
    }

    public static Connection getConnection() {
    	// Verifica se a conexão já foi criada, caso contrário, cria uma nova conexão
        if (conexao == null) {
            try {
            	 // Tenta criar a conexão utilizando a URL, usuário e senha
                conexao = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Conexão com o banco estabelecida com sucesso!");
            } catch (SQLException e) {
            	 // Caso ocorra um erro ao conectar, imprime o erro e lança uma exceção
                e.printStackTrace();
                throw new RuntimeException("Erro ao conectar ao banco de dados", e);
            }
        }
        return conexao;
    }
    
    // Método estático para fechar a conexão com o banco de dados
    public static void closeConnection() {
        if (conexao != null) {
            try {
                conexao.close();
                conexao = null;
                System.out.println("Conexão com o banco encerrada com sucesso!");
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erro ao fechar a conexão com o banco de dados", e);
            }
        }
    }
}
