package entities;
import java.util.ArrayList;
import java.util.List;

public class Carrinho {
	private String nomeDoCliente;
	private List<Produto> produtosDoCarrinho = new ArrayList<Produto>();
    private Double valorTotal;

    public Carrinho(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}

	public void addProdut(Produto prod) {
    	produtosDoCarrinho.add(prod);
    }
    
    public boolean removeProdut(Produto prod) {
    	for (int i = 0; i < produtosDoCarrinho.size(); i++) {		
    		if (produtosDoCarrinho.get(i).getNome().equals(prod.getNome())) {
    			produtosDoCarrinho.remove(prod);
    			return true;
    		}
    	} return false;
    }
    
    public String getNomeDoCliente() {
		return nomeDoCliente;
	}


	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}

	public double getValorTotal(){
        for(int i=0; i < produtosDoCarrinho.size(); i++){
           valorTotal += produtosDoCarrinho.get(i).getPreco();
        }
        return valorTotal;
    }
	
	public List<Produto> getProdutosDoCarrinho() {
		return produtosDoCarrinho;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Carrinho de " + nomeDoCliente + "\n");
		for (int i = 0; i < produtosDoCarrinho.size(); i++) {
			sb.append(i + 1 + ". " + produtosDoCarrinho.get(i).getNome() + "\n");
		} return sb.toString();
	}
}