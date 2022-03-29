package entities;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Usuario {
	private List<Carrinho> carrinho = new ArrayList<Carrinho>();
	
	public Cliente(String nome, String senha, Double saldo, String endereco) {
		super(nome, senha, saldo, endereco);
	}
	

}