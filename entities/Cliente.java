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
	}

	public String getPix() {
		return pix;
	}

	public void setPix(String pix) {
		this.pix = pix;
	}
	
	public void associaCarrinho(Carrinho carrin) {
		this.carrinho = carrin;
	}
	
	public void addNoCarrinho(Produto prod) {
		carrinho.getProdutosDoCarrinho().add(prod);
	}
	
	public void removeDoCarrinho(Produto prod) {
		carrinho.removeProdut(prod);
	}
	
	public Carrinho verCarrinho() {
		return carrinho;
	}
}