package entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import entities.exceptions.ObjetoNaoEncontradoException;

public class Ecommerce { 
	private String nome;
	private List<Categoria> categorias = new ArrayList<>();
	private List<Cliente> clientes = new ArrayList<>();
	private List<Vendedor> vendedores = new ArrayList<>();
	
	public Ecommerce(String nome) {
		this.nome = nome;
	}
	
	public String getMenu() {
		return "MENU\n1- Fazer login como cliente\n2- Fazer login como vendedor"
				+ "\n3- Fazer login como administrador\n4- Criar conta\n5- Sair"
				+ "\nDigite a opção (1/5): ";
	}
	
	public String getMenuDeContas() {
		return "\nQual tipo de conta você deseja criar?\n1- Cliente"
				+ "\n2- Vendedor\n3- Sair\nDigite a opção (1/3): ";
	}
	
	public String getMenuCRUD() {
		return "\n1- Adicionar categoria(s)\n2- Editar categoria"
				+ "\n3- Remover categoria(s)\n4- Listar categorias"
				+ "\n5- Sair\nDigite a opção (1/5): ";
	}
	
	public Categoria retornaCategoria(int catNumber) {
		return categorias.get(catNumber - 1);
	}
	
	public void editarCategoria(Categoria cat, String catNovoNome) {
		cat.setNome(catNovoNome);
		Collections.sort(categorias);
	}
	
	public boolean verificaCategoria(Categoria cat) {
		for (int i = 0; i < categorias.size(); i++) {
			// Se houver uma categoria já existente, return false
			if (categorias.get(i).getNome().equals(cat.getNome())) {
				return false;
			}
		} return true;
	}
	
	public boolean addCategoria(Categoria cat) {
		if (verificaCategoria(cat)) {
			categorias.add(cat);
			Collections.sort(categorias);
			return true;
		} return false;
	}
	
	public boolean removerCategoria(String catName) {
		for (int i = 0; i < categorias.size(); i++) {
			if (categorias.get(i).getNome().equals(catName)) {
				categorias.remove(i);
				return true;
			}
		} return false;
	}
	
	public boolean verificaVendedor(Vendedor vend) {
		for (int i = 0; i < vendedores.size(); i++) {
			// Se houver um vendedor com mesmo login já existente, return false
			if (vendedores.get(i).getNome().equals(vend.getNome())) {
				return false;
			}
		} return true;
	}
	
	public boolean verificaLoginDeVendedor(String nomeVend, String senhaVend) {
		for (int i = 0; i < vendedores.size(); i++)
			if (nomeVend.equals(vendedores.get(i).getNome()) && senhaVend.equals(vendedores.get(i).getSenha())) {
				return true;
			} return false;
	}
	
	public boolean verificaLoginDeCliente(String nomeCliente, String senhaCliente) {
		for (int i = 0; i < clientes.size(); i++)
			if (nomeCliente.equals(clientes.get(i).getNome()) && senhaCliente.equals(clientes.get(i).getSenha())) {
				return true;
			} return false;
	}
	
	public boolean addVendedor(Vendedor vend) {
		if (verificaVendedor(vend)) {
			vendedores.add(vend);
			return true;
		} return false;
	}
	
	public boolean verificaCliente(Cliente cliente) {
		for (int i = 0; i < clientes.size(); i++) {
			// Se houver um cliente com mesmo login já existente, return false
			if (clientes.get(i).getNome().equals(cliente.getNome())) {
				return false;
			}
		} return true;
	}
	
	public boolean addCliente(Cliente cliente) {
		if (verificaCliente(cliente)) {
			clientes.add(cliente);
			return true;
		} return false;
	}
	
	public Cliente achaCliente(String nomeCliente) throws ObjetoNaoEncontradoException {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNome().equals(nomeCliente)) {
				return clientes.get(i);
			}
		} throw new ObjetoNaoEncontradoException("Não há nenhum cliente com este nome!");
	}
	
	public String getNome() {
		return nome;
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
		} return sb.toString();
	}
}