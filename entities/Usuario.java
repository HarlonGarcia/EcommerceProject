package entities;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	protected String nome;
	protected String senha;
	protected Double saldo;
	protected String endereco;
	protected String cpf;
	
	public Usuario() {
	}
	
	public Usuario(String nome, String senha, Double saldo, String endereco, String cpf) {
		this.nome = nome;
		this.senha = senha;
		this.saldo = saldo;
		this.endereco = endereco;
		this.cpf = cpf;
	}
	
	/*
	private void writeObject(ObjectOutputStream oss) {
		try {
			oss.defaultWriteObject();
			oss.writeUTF(endereco.getRua());
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private void readObject(ObjectInputStream ois) {
		try {
			ois.defaultReadObject();
			endereco = new Endereco(ois.readUTF());
		} catch(IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/

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

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String toString() {
		return "Nome do usuário: " + nome + "\nEndereco: " + endereco + "\nCPF: " + cpf;
	}
}
