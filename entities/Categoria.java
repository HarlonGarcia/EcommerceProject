package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Categoria implements Comparable<Categoria>{
	protected String nome;
	protected List<Subcategoria> subcategorias = new ArrayList<Subcategoria>();
	
	public Categoria() {
	}

	public Categoria(String nome) {
		this.nome = nome;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public boolean verificaSubcategoria(Subcategoria sub) {
		for (int i = 0; i < subcategorias.size(); i++) {
			// Se houver uma subcategoria já existente, return false
			if (subcategorias.get(i).getNome().equals(sub.getNome())) {
				return false;
			}
		} 
		return true;
	}
	
	public boolean addSubcategoria(Subcategoria sub) {
		if (verificaSubcategoria(sub)) {
			subcategorias.add(sub);
			Collections.sort(subcategorias);
			return true;
		}
		return false;
	}
	
	public int compareTo(Categoria cat) {
		return this.getNome().compareTo(cat.getNome());
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nome.toUpperCase() + "\n");
		for (int i=0; i < subcategorias.size(); i++) {
			sb.append(i + 1 + ". " + subcategorias.get(i).getNome() + "\n");
		}
		return sb.toString();
	}
}
