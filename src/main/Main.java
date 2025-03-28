package main;

import view.CategoriaView;
import view.ProdutoView;
import view.RelatorioView;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    @SuppressWarnings("resource")
	public static void main(String[] args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        CategoriaView categoriaView = new CategoriaView();
        ProdutoView produtoView = new ProdutoView();
        RelatorioView relatorioView = new RelatorioView();

        while (true) {
            System.out.println("\n--- Menu Principal ---");
            System.out.println("1. Categorias");
            System.out.println("2. Produtos");
            System.out.println("3. Relatórios");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    categoriaView.exibirMenu();
                    break;
                case 2:
                    produtoView.exibirMenu();
                    break;
                case 3:
                    relatorioView.exibirMenu();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
