package entities;

import java.util.ArrayList;
import java.util.List;

public class Vendedor extends Usuario {
	private static final long serialVersionUID = 1L;
	private List<Produto> produtosAnunciados = new ArrayList<Produto>();
	private String pix;
	
	public Vendedor() {
	}

	public Vendedor(String nome, String senha, Double saldo, String endereco, String cpf, String pix) {
		super(nome, senha, saldo, endereco, cpf);
		this.pix = pix;
	}
	
	public void novoProdutoAnunciado(Produto produto) {
		produtosAnunciados.add(produto);
	}
	
	public String getProdutosAnunciados() {
		StringBuilder sb = new StringBuilder();
		sb.append("Produtos anunciados por " + this.getNome() + "\n");
		for (int i = 0; i < produtosAnunciados.size(); i++) {
			sb.append(i+1 + ". " + produtosAnunciados.get(i).getNome() + "\n");
		}
		return sb.toString();
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