package entities;

import java.util.List;

public class Carrinho {
	private List<Produto> produtos;
    private Double valorTotal;

    public double getValorTotal(){
        for(int i=0; i<produtos.size(); i++){
           valorTotal += produtos.get(i).getPreco();
        }
        return valorTotal;
    }

}
