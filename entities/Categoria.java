package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria implements Comparable<Categoria>{
	protected String nome;
	protected List<Produto> produtos = new ArrayList<Produto>();
	
	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public boolean verificaProduto(Produto prod) {
		for (int i = 0; i < produtos.size(); i++) {
			// Se houver um produto igual já existente, return false
			if (produtos.get(i).getNome().equals(prod.getNome())) {
				return false;
			}
		} return true;
	}
	
	public boolean addProduto(Produto prod) {
		if (verificaProduto(prod)) {
			produtos.add(prod);
			Collections.sort(produtos);
			return true;
		} return false;
	}
	
	public void removerProduto(Produto produto) {
		produtos.remove(produto);
	}
	
	public void editarProduto(Produto prod, String prodNovoNome) {
		prod.setNome(prodNovoNome);
		Collections.sort(produtos);
	}
	
	public Produto retornaProduto(int prodNumber) {
		return produtos.get(prodNumber - 1);
	}
	
	public String indexOfProduto(String nome) {
		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getNome().equals(nome)) {
				return produtos.get(i).getNome();
			} 
		}
		return null;
	}
	
	public int compareTo(Categoria cat) {
		return this.getNome().compareTo(cat.getNome());
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nome.toUpperCase() + "\n");
		for (int i = 0; i < produtos.size(); i++) {
			sb.append(i + 1 + ". " + produtos.get(i).getNome() + "\n");
		} return sb.toString();
	}
}
