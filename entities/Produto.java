package entities;

public class Produto implements Comparable<Produto>{
	private String nome;
	private Double preco;
	private String descricao;
	
	public Produto() {
	}
	
	public Produto(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
	}
	
	public final int compareTo(Produto prod) {
		return this.getNome().compareTo(prod.getNome());
	}
	
	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		return "Nome do produto: " + nome + "\nPreço: " + preco
				+ "\nDescrição: " + descricao;
	}

}
