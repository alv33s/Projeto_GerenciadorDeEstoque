package view;

import controller.ProdutoController;
import model.Produto;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProdutoView {

    private ProdutoController produtoController;
    private Scanner scanner;

    public ProdutoView() {
        produtoController = new ProdutoController();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() throws SQLException {
        while (true) {
            System.out.println("\n--- Menu Produto ---");
            System.out.println("1. Cadastrar Produto");
            System.out.println("2. Editar Produto");
            System.out.println("3. Excluir Produto");
            System.out.println("4. Listar Produtos");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    editarProduto();
                    break;
                case 3:
                    excluirProduto();
                    break;
                case 4:
                    listarProdutos();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarProduto() throws SQLException {
        System.out.print("Nome do Produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição do Produto: ");
        String descricao = scanner.nextLine();
        System.out.print("Quantidade em Estoque: ");
        int quantidadeEstoque = scanner.nextInt();
        System.out.print("Preço de Compra: ");
        double precoCompra = scanner.nextDouble();
        System.out.print("Preço de Venda: ");
        double precoVenda = scanner.nextDouble();
        System.out.print("ID da Categoria: ");
        int idCategoria = scanner.nextInt();
        produtoController.cadastrarProduto(nome, descricao, quantidadeEstoque, precoCompra, precoVenda, idCategoria);
        System.out.println("Produto cadastrado com sucesso!");
    }

    private void editarProduto() {
        System.out.print("ID do Produto para editar: ");
        int idProduto = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Nova Quantidade: ");
        int quantidade = scanner.nextInt();
        System.out.print("Novo Preço de Compra: ");
        double precoCompra = scanner.nextDouble();
        System.out.print("Novo Preço de Venda: ");
        double precoVenda = scanner.nextDouble();
        System.out.print("Novo ID da Categoria: ");
        int idCategoria = scanner.nextInt();
        try {
            produtoController.editarProduto(idProduto, nome, descricao, quantidade, precoCompra, precoVenda, idCategoria);
            System.out.println("Produto atualizado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao editar produto: " + e.getMessage());
        }
    }

    private void excluirProduto() {
        System.out.print("ID do Produto para excluir: ");
        int idProduto = scanner.nextInt();
        try {
            produtoController.excluirProduto(idProduto);
            System.out.println("Produto excluído com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao excluir produto: " + e.getMessage());
        }
    }

    private void listarProdutos() {
        System.out.print("Nome do Produto (deixe vazio para todos): ");
        String nome = scanner.nextLine();
        System.out.print("ID da Categoria (0 para todas): ");
        int idCategoria = scanner.nextInt();
        System.out.print("Quantidade em Estoque (deixe em branco para todos): ");
        Integer quantidadeEstoque = scanner.nextInt();
        try {
            List<Produto> produtos = produtoController.consultarProdutos(nome, idCategoria, quantidadeEstoque);
            if (produtos.isEmpty()) {
                System.out.println("Nenhum produto encontrado.");
            } else {
                for (Produto produto : produtos) {
                    System.out.println("ID: " + produto.getId() + ", Nome: " + produto.getNome() + ", Estoque: " + produto.getQuantidadeEstoque() + ", Preço Compra: " + produto.getPrecoCompra() + ", Preço Venda: " + produto.getPrecoVenda());
                }
            }
        } catch (Exception e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }
}
