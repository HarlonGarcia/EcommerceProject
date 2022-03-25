package entities;

public class Usuario {
	protected String nome;
	protected String senha;
	protected Double saldo;
	protected Endereco endereco;
	
	public Usuario() {
	}
	
	public Usuario(String nome, String senha, Double saldo, Endereco endereco) {
		this.nome = nome;
		this.senha = senha;
		this.saldo = saldo;
		this.endereco = endereco;
	}

	public void addSaldo(double valor) {
		this.saldo += valor;
	}

	public void removeSaldo(double valor) {
		this.saldo -= valor;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public double getSaldo() {
		return saldo;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

}
