package entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Subcategoria extends Categoria {
	private List<Produto> produtos = new ArrayList<>();
	
	public Subcategoria() {
		super();
	}
	
	public Subcategoria(String nome) {
		super(nome);
	}

	public boolean verificaProduto(Produto prod) {
		for (int i = 0; i < produtos.size(); i++) {
			// Se houver um produto igual já existente, return false
			if (produtos.get(i).getNome().equals(prod.getNome())) {
				return false;
			}
		} 
		return true;
	}
	
	public boolean addProduto(Produto prod) {
		if (verificaProduto(prod)) {
			produtos.add(prod);
			Collections.sort(produtos);
			return true;
		}
		return false;
	}
	
	@Override
	public int compareTo(Categoria sub) {
		for (int i = 0; i < subcategorias.size(); i++) {
			return subcategorias.get(i).getNome().compareTo(sub.getNome());
		}
		return 0;
	}
	
	public void removeProduto(Produto produto) {
		produtos.remove(produto);
	}
	
	public String acharProduto(String nome) {
		for (int i = 0; i < produtos.size(); i++) {
			if (produtos.get(i).getNome().equals(nome)) {
				return produtos.get(i).getNome();
			} 
		}
		return null;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nome.toUpperCase() + "\n");
		for (int i=0; i < produtos.size(); i++) {
			sb.append(i + 1 + ". " + produtos.get(i).getNome() + "\n");
		}
		return sb.toString();
	}
}
