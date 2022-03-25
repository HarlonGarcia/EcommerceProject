package entities;

import java.util.List;

public class Pagamento {
	private List<Cliente> clientes;
	public static final String CODIGO = "000000";
	
	/**
	 * 
	 * @param Cliente que realiza o pagamento
	 * @param Valor a ser pago com cartao
	 * @return Um codigo que identifica a venda ou retorna null caso nao ache a venda
	 */
	public String pagarComPix(Cliente cliente, Vendedor vendedor, Double valor) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNome().equals(cliente.getNome())) {			
				clientes.get(i).removeSaldo(valor);
				Venda venda = new Venda(vendedor);
				return venda.getRandomNumber();
			}
		}
		return CODIGO;
	}

	/** Efetua a compra e automaticamente diminui o saldo do cliente o valor
	 * com uma pequena taxa do cartao
	 * gerando um codigo da venda
	 * 
	 * @param Cliente que realiza o pagamento
	 * @param Valor a ser pago com cartao
	 * @return Um codigo que identifica a venda ou retorna null caso nao ache a venda
	 */

	public String pagarComCartao(Cliente cliente, Vendedor vendedor, Double valor) {
		for (int i = 0; i < clientes.size(); i++) {
			if (clientes.get(i).getNome().equals(cliente.getNome())) {			
				clientes.get(i).removeSaldo(valor + (valor/100)*5);
				Venda venda = new Venda(vendedor);
				return venda.getRandomNumber();
			}
		}
		return CODIGO;
	}
}
