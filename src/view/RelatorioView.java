package view;

import controller.RelatorioController;
import java.util.Scanner;

public class RelatorioView {

    private RelatorioController relatorioController;
    private Scanner scanner;

    public RelatorioView() {
        relatorioController = new RelatorioController();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n--- Menu Relatório ---");
            System.out.println("1. Relatório de Produtos");
            System.out.println("2. Relatório de Movimentações");
            System.out.println("3. Relatório de Baixo Estoque");
            System.out.println("4. Relatório de Lucro");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    gerarRelatorioProdutos();
                    break;
                case 2:
                    gerarRelatorioMovimentacoes();
                    break;
                case 3:
                    gerarRelatorioBaixoEstoque();
                    break;
                case 4:
                    gerarRelatorioLucro();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void gerarRelatorioProdutos() {
        System.out.println(relatorioController.gerarRelatorioProdutos());
    }

    private void gerarRelatorioMovimentacoes() {
        System.out.println(relatorioController.gerarRelatorioMovimentacoes());
    }

    private void gerarRelatorioBaixoEstoque() {
        System.out.println(relatorioController.gerarRelatorioBaixoEstoque());
    }

    private void gerarRelatorioLucro() {
        System.out.println(relatorioController.gerarRelatorioLucro());
    }
}
