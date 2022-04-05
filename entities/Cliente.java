package entities;

public class Cliente extends Usuario {
	private static final long serialVersionUID = 1L;
	private String pix;
	private Carrinho carrinho;

	public Cliente() {
		super();
	}
	
	public Cliente(String nome, String senha, Double saldo, String endereco, String cpf, String pix) {
		super(nome, senha, saldo, endereco, cpf);
		this.pix = pix;
		this.carrinho = new Carrinho(nome);
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}
	
	public void addNoCarrinho(Produto prod) {
		carrinho.addProdut(prod);
	}
	
	public void removeDoCarrinho(Produto prod) {
		carrinho.removeProdut(prod);
	}
	
	public Carrinho getCarrinho() {
		return carrinho;
	}
}