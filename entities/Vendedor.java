package entities;

public class Vendedor extends Usuario {
	private static final long serialVersionUID = 1L;
	private String pix;
	
	public Vendedor() {
	}

	public Vendedor(String nome, String senha, Double saldo, String endereco, String cpf, String pix) {
		super(nome, senha, saldo, endereco, cpf);
		this.pix = pix;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}
}