# 📜Projeto Gerenciador de estoque
Nesse projeto foi desenvolvido um gerenciador de estoque ligado a um banco de dados utilizando a linguagem Java com a arquitetura de projeto MVC(model, view, controller)

## 🚧Estrutura do projeto

 - 📂settings
 - 📂bin
 - 📂src
 - `.classpath`
 - `.project`
 -  `DocumentaçãoDoProjeto`
 -  `Script-AT2-N2.sql`

  ### 📥Classes
   - 📂conexaoDB
    - `DBConnection.class`
   - 📂model
     - `Categoria.class`
     - `Produto.class`
   - 📂view
     - `CategoriaView.class`
     - `ProdutoView.class`
     - `RelatórioView.class`
   - 📂controller
     - `CategoriaController.class`
     - `ProdutoController.class`
     - `RelatórioController.class`
   - 📂dao
     - `CategoriaDAO.class`
     - `ProdutoDAO.class`
     - `RelatórioDAO.class`
   - 📂main
     - `Main.class`
    

  ### 📋Tabelas do banco de dados
   - Categoria
   - Produto
   - MovimentaçãoEstoque  

## 🔌Funcionalidades

  - Conexão com o Banco de dados
  - Cadastrar, excluir ,editar e consultar categorias
  - Cadastrar, excluir ,editar e consultar produtos
  - Gerar relatório de produtos
  - Gerar relatórios de movimentações financeiras
  - Gerar relatório de baixo estoque
  - Gerar relatório de lucro

  ### 🍃Fluxo do Sistema-
   - Conexão com o Banco de Dados: A classe DBConnection gerencia a conexão com o banco de dados MySQL.
   - Operações CRUD: O sistema permite CRUD completo para as entidades Produto e Categoria, gerenciando os dados no        banco de dados.
   - Filtros: Produtos podem ser filtrados por nome, categoria e quantidade no estoque.
   - Relatórios: O sistema pode gerar relatórios sobre os produtos cadastrados, lucro e produtos com baixo estoque.
   - Funcionalidades Principais
     - Cadastro e Manipulação de Produtos: Cadastro, atualização, exclusão e consulta de produtos com filtros.
     - Gerenciamento de Categorias: Cadastro, edição e exclusão de categorias.
     - Relatórios: Exibição de relatórios com informações detalhadas dos produtos.
     - Interface Gráfica: Interface gráfica construída no prompt de facil navegação.****
  

## Autor
Felipe Alves Muniz
