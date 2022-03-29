package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ecommerce { 
	private String nome;
	private List<Categoria> categorias = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	private List<Vendedor> vendedores = new ArrayList<>();
	
	public Ecommerce(String nome) {
		this.nome = nome;
	}
	
	public boolean verificaCategoria(Categoria cat) {
		for (int i = 0; i < categorias.size(); i++) {
			// Se houver uma categoria já existente, return false
			if (categorias.get(i).getNome().equals(cat.getNome())) {
				return false;
			}
		} 
		return true;
	}
	
	public Categoria retornaCategoria(int catNumber) {
		return categorias.get(catNumber - 1);
	}
	
	public void editarCategoria(Categoria cat, String catNovoNome) {
		cat.setNome(catNovoNome);
		Collections.sort(categorias);
	}
	
	public int indexOfCategoria(String catName) {
		for (int i = 0; i < categorias.size(); i++) {
			if (categorias.get(i).getNome().equals(catName)) {
				return i;
			}
		} 
		return -1;
	}
	
	public boolean addCategoria(Categoria cat) {
		if (verificaCategoria(cat)) {
			categorias.add(cat);
			Collections.sort(categorias);
			return true;
		}
		return false;
	}
	
	public boolean removerCategoria(String catName) {
		for (int i = 0; i < categorias.size(); i++) {
			if (categorias.get(i).getNome().equals(catName)) {
				categorias.remove(i);
				return true;
			}
		} return false;
	}

	public String getNome() {
		return nome;
	}

	public String getMenu() {
		return "MENU\n1- Fazer login como cliente\n2- Fazer login como vendedor"
				+ "\n3- Fazer login como administrador\n4- Criar conta\n5- Sair"
				+ "\nDigite a opção (1/5): ";
	}

	public String getMenuDeContas() {
		return "\nQual tipo de conta você deseja criar?\n1- Cliente"
				+ "\n2- Vendedor\nDigite a opção (1/2): ";
	}
	
	public String getMenuCRUD() {
		return "\n1- Adicionar categoria(s)\n2- Editar categoria"
				+ "\n3- Remover categoria(s)\n4- Listar categorias"
				+ "\n5- Sair\nDigite a opção (1/5): ";
	}
	
	public List<Cliente> getClientes() {
		return clientes;
	}

	public List<Vendedor> getVendedores() {
		return vendedores;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(nome.toUpperCase() + "\n");
		for (int i=0; i < categorias.size(); i++) {
			sb.append(i + 1 + ". " + categorias.get(i).getNome() + "\n");
		}
		return sb.toString();
	}

}