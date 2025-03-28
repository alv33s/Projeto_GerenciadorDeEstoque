package controller;

import dao.CategoriaDAO;
import model.Categoria;
import java.util.List;

public class CategoriaController {
	   // Instância do DAO que gerencia a persistência das categorias
    private CategoriaDAO categoriaDAO;

    public CategoriaController() {
        categoriaDAO = new CategoriaDAO();
    }

    public void cadastrarCategoria(int id,String nome, String descricao) {
        Categoria novaCategoria = new Categoria(id,nome, descricao);
        categoriaDAO.inserirCategoria(novaCategoria);
    }

    public void editarCategoria(int id, String nome, String descricao) {
        Categoria categoria = categoriaDAO.listarCategorias().stream()
                .filter(c -> c.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Categoria com ID " + id + " não encontrada."));
        categoria.setNome(nome);
        categoria.setDescricao(descricao);
        categoriaDAO.atualizarCategoria(categoria);
    }
    
    
    public void excluirCategoria(int id) {
        categoriaDAO.excluirCategoria(id);
    }
    
    // Método para consultar todas as categorias
    public List<Categoria> consultarCategorias() {
        return categoriaDAO.listarCategorias(); // Agora chamando DAO para pegar categorias do banco de dados
    }
}
