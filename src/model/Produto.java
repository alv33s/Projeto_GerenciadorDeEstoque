package model;

public class Produto {
	
	    private int id;
	    private String nome;
	    private String descricao;
	    private int quantidadeEstoque;
	    private double precoCompra;
	    private double precoVenda;
	    private int categoriaId;
	    
	    public Produto() {};
	    
		public Produto(int i, String string, String string2, int j, double d, double e, String string3) {};
	    
		public Produto(int id, String nome, String descricao, int quantidadeEstoque, double precoCompra,
				double precoVenda, int categoriaId) {
			super();
			this.id = id;
			this.nome = nome;
			this.descricao = descricao;
			this.quantidadeEstoque = quantidadeEstoque;
			this.precoCompra = precoCompra;
			this.precoVenda = precoVenda;
			this.categoriaId = categoriaId;
		}


		public int getId() {
			return id;
		}


		public void setId(int id) {
			this.id = id;
		}


		public String getNome() {
			return nome;
		}


		public void setNome(String nome) {
			this.nome = nome;
		}


		public String getDescricao() {
			return descricao;
		}


		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}


		public int getQuantidadeEstoque() {
			return quantidadeEstoque;
		}


		public void setQuantidadeEstoque(int quantidadeEstoque) {
			this.quantidadeEstoque = quantidadeEstoque;
		}


		public double getPrecoCompra() {
			return precoCompra;
		}


		public void setPrecoCompra(double precoCompra) {
			this.precoCompra = precoCompra;
		}


		public double getPrecoVenda() {
			return precoVenda;
		}


		public void setPrecoVenda(double precoVenda) {
			this.precoVenda = precoVenda;
		}


		public int getCategoriaId() {
			return categoriaId;
		}


		public void setCategoriaId(int categoriaId) {
			this.categoriaId = categoriaId;
		}

		
	    
	

}
