package view;

import controller.CategoriaController;
import model.Categoria;
import java.util.List;
import java.util.Scanner;

public class CategoriaView {

    private CategoriaController categoriaController;
    private Scanner scanner;

    public CategoriaView() {
        categoriaController = new CategoriaController();
        scanner = new Scanner(System.in);
    }

    public void exibirMenu() {
        while (true) {
            System.out.println("\n--- Menu Categoria ---");
            System.out.println("1. Cadastrar Categoria");
            System.out.println("2. Editar Categoria");
            System.out.println("3. Excluir Categoria");
            System.out.println("4. Listar Categorias");
            System.out.println("5. Voltar");
            System.out.print("Escolha uma opção: ");
            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a quebra de linha

            switch (opcao) {
                case 1:
                    cadastrarCategoria();
                    break;
                case 2:
                    editarCategoria();
                    break;
                case 3:
                    excluirCategoria();
                    break;
                case 4:
                    listarCategorias();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarCategoria() {
        System.out.print("Nome da Categoria: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição da Categoria: ");
        String descricao = scanner.nextLine();
        int id = 0;
		categoriaController.cadastrarCategoria(id,nome, descricao);
        System.out.println("Categoria cadastrada com sucesso!");
    }

    private void editarCategoria() {
        System.out.print("ID da Categoria para editar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        System.out.print("Novo Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Nova Descrição: ");
        String descricao = scanner.nextLine();
        categoriaController.editarCategoria(id, nome, descricao);
        System.out.println("Categoria atualizada com sucesso!");
    }

    private void excluirCategoria() {
        System.out.print("ID da Categoria para excluir: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consumir a quebra de linha
        categoriaController.excluirCategoria(id);
        System.out.println("Categoria excluída com sucesso!");
    }

    private void listarCategorias() {
        List<Categoria> categorias = categoriaController.consultarCategorias();
        if (categorias.isEmpty()) {
            System.out.println("Nenhuma categoria cadastrada.");
        } else {
            for (Categoria categoria : categorias) {
                System.out.println("ID: " + categoria.getId() + ", Nome: " + categoria.getNome() + ", Descrição: " + categoria.getDescricao());
            }
        }
    }
}
