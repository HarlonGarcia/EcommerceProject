package entities;

import java.util.Random;

public class Venda {
	private Vendedor vendedor;
	private int codigo;
	
	
	public Venda(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public int getCodigo() {
		return codigo;
	}

	// Gera código de venda e armazena no atributo
	public String getRandomNumber() {
		Random codigo = new Random();
		int number = codigo.nextInt(999999);
		this.codigo = number;
		
		return String.format("%06d", number);
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}
	
}
