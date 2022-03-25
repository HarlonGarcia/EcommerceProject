package entities;

import entities.exceptions.SenhaInvalidaException;

public class Administrador {
	private String nome;
	public static final String SENHA = "3301";
	
	public Administrador(String nome, String senha) throws SenhaInvalidaException {
		if (senha.equals(SENHA)) {
			this.nome = nome;
		} else {
			throw new SenhaInvalidaException("Senha inválida!");
		}
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return SENHA;
	}
}
