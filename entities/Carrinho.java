package entities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Carrinho {
	private String nomeDoCliente;
	private List<Produto> produtosDoCarrinho = new ArrayList<Produto>();
    private double valorTotal;

    public Carrinho(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}

	public void addProdut(Produto prod) {
    	produtosDoCarrinho.add(prod);
    	Collections.sort(produtosDoCarrinho);
    	this.addValorTotal(prod.getPreco());
    }
    
    public boolean removeProdut(Produto prod) {
    	for (int i = 0; i < produtosDoCarrinho.size(); i++) {		
    		if (produtosDoCarrinho.get(i).getNome().equals(prod.getNome())) {
    			produtosDoCarrinho.remove(prod);
    			return true;
    		}
    	} return false;
    }
    
    public Produto retornaProdutoDoCarrinho(String nome) {
    	for (int i = 0; i < produtosDoCarrinho.size(); i++) {
    		if (produtosDoCarrinho.get(i).getNome().equals(nome)) {
    			return produtosDoCarrinho.get(i);
    		}
    	} return null;
    }

    static int calcular(String x, String y) {  // Retorna número de edições
		if (x.isEmpty()) {
			return y.length();
		}

		if (y.isEmpty()) {
			return x.length();
		} 

		int substituicao = calcular(x.substring(1), y.substring(1)) 
				+ custoDaSubstituicao(x.charAt(0), y.charAt(0));
		int insercao = calcular(x, y.substring(1)) + 1;
		int delecao = calcular(x.substring(1), y) + 1;

		return min(substituicao, insercao, delecao);
	}

	public static int custoDaSubstituicao(char a, char b) { 
		return a == b ? 0 : 1;
	}

	public static int min(int... numeros) {
		return Arrays.stream(numeros)
				.min().orElse(Integer.MAX_VALUE);
	}
	
	public String algoritmoLevenshtein(String nomeTronxo) { // Verifica palavra com menos edições
		int edicoes = 100;
		String palavraCerta = "";
		for (int i = 0; i < produtosDoCarrinho.size(); i++) {
			int numero = calcular(produtosDoCarrinho.get(i).getNome(), nomeTronxo);
			if (numero < edicoes) {
				edicoes = numero;
				palavraCerta = produtosDoCarrinho.get(i).getNome();
			}
		}
		return palavraCerta; // Retorna palavra mais próxima
	}
	
	public int qtdDeProdutosNoCarrin() {
		return produtosDoCarrinho.size();
	}
    public String getNomeDoCliente() {
		return nomeDoCliente;
	}

	public void setNomeDoCliente(String nomeDoCliente) {
		this.nomeDoCliente = nomeDoCliente;
	}

	public double getValorTotal(){
        return valorTotal;
    }
	
	public void addValorTotal(Double valor){
        this.valorTotal += valor;
    }
	
	public List<Produto> getProdutosDoCarrinho() {
		return produtosDoCarrinho;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Carrinho de " + nomeDoCliente + "\n");
		for (int i = 0; i < produtosDoCarrinho.size(); i++) {
			sb.append(i + 1 + ". " + produtosDoCarrinho.get(i).getNome() + "\n");
		} 
		sb.append(String.format("\nValor total do carrinho: %.2f\n", this.getValorTotal()));
		return sb.toString();
	}
}