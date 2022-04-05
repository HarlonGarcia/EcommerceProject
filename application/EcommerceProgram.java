package application;
import java.util.Locale;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Scanner;
import entities.Administrador;
import entities.Categoria;
import entities.Cliente;
import entities.Ecommerce;
import entities.Produto;
import entities.Vendedor;
import entities.exceptions.ObjetoNaoEncontradoException;
import entities.exceptions.SenhaInvalidaException;

public class EcommerceProgram {

	public static void main(String[] args) throws ObjetoNaoEncontradoException {
		
		Scanner sc = new Scanner(System.in);
		Locale.setDefault(Locale.US);
		Ecommerce ec = new Ecommerce("Feira Ecommerce");
		
		Vendedor v = new Vendedor("Pp", "321", 0.00, "Buraco da Gia", "032.719.540-45", "8396450328");
		Categoria cat1 = new Categoria("Tecnologia");
		Categoria cat2 = new Categoria("Roupas");
		ec.addCategoria(cat1);
		ec.addCategoria(cat2);
		Produto prod1 = new Produto("Notebook", 3899.00, "Macbook da Shopee", v);
		Produto prod2 = new Produto("Celular", 2900.00, "Xiaomi da Apple", v);
		Produto prod3 = new Produto("Cueca", 25.00, "Cueca nova, só tá rasgada em baixo", v);
		cat1.addProduto(prod1);
		cat1.addProduto(prod2);
		cat2.addProduto(prod3);
		v.novoProdutoAnunciado(prod1);
		v.novoProdutoAnunciado(prod2);
		v.novoProdutoAnunciado(prod3);
		Cliente c = new Cliente("Jemão", "123", 9000.00, "Rua 1", "427.902.256-90", "8390038744");
		ec.addCliente(c);
		ec.addVendedor(v);
		
		System.out.printf("Bem vindo ao %s!\n", ec.getNome());
		int opcao;
		do {
			opcao = inputNumero(sc, "\n" + ec.getMenu());
			
			switch (opcao) {
			case 1: // LOGIN CLIENTE
				boolean clienteLogado = false;
				
				System.out.print("\nDigite seu nome: ");
				String nomeDoCliente = sc.nextLine();
				System.out.print("Digite sua senha: ");
				String senhaDoCliente = sc.nextLine();
				
				if (ec.verificaLoginDeCliente(nomeDoCliente, senhaDoCliente)) {
					clienteLogado = true;
				} else {
					clienteLogado = false;
					System.out.println("Senha Inválida!");
				}
				
				if (clienteLogado) {
					do {
						int numEscolhido = inputNumero(sc, "\nMENU\n1- Comprar produto\n2- Ver carrinho e detalhes"
								+ "\n3- Ver saldo\n4- Sair\nDigite uma das opções (1/4): ");
					
						if (numEscolhido == 1) { // COMPRAR PRODUTO
							System.out.print("\n" + ec);
							int catEscolhida = inputNumero(sc, "\nEscolha uma das categorias: ");
							Categoria cat = ec.retornaCategoria(catEscolhida);
							System.out.print("\n" + cat);
							int prodEscolhido = inputNumero(sc, "\nQual produto deseja adicionar ao carrinho? ");
							Produto prod = cat.retornaProduto(prodEscolhido);
							Cliente cliente = ec.getCliente(nomeDoCliente);
							Vendedor vendedor = prod.getVendedor();
							if (prod.getPreco() > cliente.getSaldo()) {
								System.out.print("\nSeu saldo é insuficiente!\n");
							} else {	
								vendedor.addSaldo(prod.getPreco());
								cliente.removeSaldo(prod.getPreco());
								cliente.addNoCarrinho(prod);
								System.out.printf("\nProduto adicionado ao carrinho!\nValor da compra: R$%.3f\n", prod.getPreco());
							}
							System.out.printf("Seu saldo atual é de R$%.2f!\n", cliente.getSaldo());
						} else if (numEscolhido == 2) { // EXIBIR CARRINHO E DETALHES
							Cliente cliente = ec.getCliente(nomeDoCliente);
							if (cliente.getCarrinho().qtdDeProdutosNoCarrin() >= 1) {
								System.out.print("\n" + cliente.getCarrinho());
								System.out.print("\nDigite o nome do produto para ver detalhes: ");
								String nomeDigitado = sc.nextLine();
								if (cliente.getCarrinho().algoritmoLevenshtein(nomeDigitado).equals(nomeDigitado)) {
									System.out.print("\n" + cliente.getCarrinho().retornaProdutoDoCarrinho(nomeDigitado) + "\n");
								} else {
									String palavra = cliente.getCarrinho().algoritmoLevenshtein(nomeDigitado);
									System.out.printf("\nVocê quis dizer %s? ", palavra);
									String simOuNao = sc.nextLine().toUpperCase();
									if (simOuNao.equals("SIM") || simOuNao.equals("S")) {
										System.out.print("\n" + cliente.getCarrinho().retornaProdutoDoCarrinho(palavra) + "\n");
									} else {
										System.out.print("\nNão conseguimos achar um produto com o nome que foi digitado!\n");
									}
								}
							} else {
								System.out.print("\nAinda não há nenhum produto no carrinho!\n");
							}
						} else if (numEscolhido == 3) { // EXIBIR SALDO
							Cliente cliente = ec.getCliente(nomeDoCliente);
							System.out.printf("\nSeu saldo atual é de R$%.2f!\n", cliente.getSaldo());
						} else if (numEscolhido == 4){ // SAIR
							clienteLogado = false;
						} else {
							System.out.println("\nDigite uma das opções possíveis!");
						}
					} while (clienteLogado);
				}
				break;	
			case 2: // LOGIN VENDEDOR
				boolean vendedorLogado = false;
				
				System.out.print("\nDigite seu nome: ");
				String nomeVendedor = sc.nextLine();
				System.out.print("Digite sua senha: ");
				String senhaVendedor = sc.nextLine();
				
				if (ec.verificaLoginDeVendedor(nomeVendedor, senhaVendedor)) {
					vendedorLogado = true;
				} else {
					vendedorLogado = false;
					System.out.println("Senha Inválida!");
				}
				
				while (vendedorLogado) {
					int numEscolhido = inputNumero(sc, "\nMENU DO VENDEDOR\n1- Adicionar produto\n2- Editar produto"
							+ "\n3- Ver produtos anunciados\n4- Ver saldo\n5- Sair\nDigite umas das opções (1/5): ");
						
					if (numEscolhido == 1){ // ADICIONAR PRODUTO
						System.out.print("\n" + ec);
						int numCat = inputNumero(sc, "\nEm qual das categorias deseja adicionar? ");
						Categoria cat = ec.retornaCategoria(numCat);
						int quantProd = inputNumero(sc, "\nQuantos produtos deseja adicionar? ");
						System.out.println();
							
						for (int i = 0; i < quantProd; i++) {
							System.out.print("Qual nome do produto que deseja adicionar? ");
							String prodNome = sc.nextLine();
							System.out.print("Qual preço do produto? ");
							Double prodPreco = Double.parseDouble(sc.nextLine());
							System.out.print("Dê uma descrição para este produto: ");
							String prodDesc = sc.nextLine();
							Vendedor vendedor = ec.getVendedor(nomeVendedor);
							Produto product = new Produto(prodNome, prodPreco, prodDesc, vendedor);
							vendedor.novoProdutoAnunciado(product);
							cat.addProduto(product);
							System.out.println();
						}
						System.out.print(cat);
					} else if (numEscolhido == 2){ // EDITAR PRODUTO
						System.out.print("\n" + ec);
						int numCat = inputNumero(sc, "\nEm qual das categorias está o produto? ");
						Categoria cat = ec.retornaCategoria(numCat);
						System.out.print("\n" + cat);
						int numProd = inputNumero(sc, "\nQual produto deseja editar? ");
						Produto prod = cat.retornaProduto(numProd);
						int opcaoEscolhida = inputNumero(sc, "\nQual alteração deseja realizar?\n1- Editar nome\n2- Editar preço"
								+ "\n3- Editar descrição\nDigite uma das opções: ");
						if (opcaoEscolhida == 1) {
							System.out.print("\nDigite o novo nome do produto: ");
							String novoNomeProd = sc.nextLine();
							prod.setNome(novoNomeProd);
						} else if (opcaoEscolhida == 2) {
							System.out.print("\nDigite o novo preço do produto: ");
							Double novoPrecoProd = Double.parseDouble(sc.nextLine());
							prod.setPreco(novoPrecoProd);
						} else if (opcaoEscolhida == 3) {
							System.out.print("\nDigite a nova descrição do produto: ");
							String novoDescProd = sc.nextLine();
							prod.setDescricao(novoDescProd);
						} else {
							System.out.println("\nDigite uma das opções possíveis!");
						}
						System.out.print("\n" + prod + "\n");
					} else if (numEscolhido == 3){ // LISTAR PRODUTOS ANUNCIADOS
						Vendedor vendedor = ec.getVendedor(nomeVendedor);
						System.out.print("\n" + vendedor.getProdutosAnunciados());
					} else if (numEscolhido == 4){ // EXIBIR SALDO
						Vendedor vendedor = ec.getVendedor(nomeVendedor);
						System.out.printf("\nSeu saldo atual é de R$%.2f!\n", vendedor.getSaldo());
					} else if (numEscolhido == 5){ // SAIR
						vendedorLogado = false;
					} else {
						System.out.println("\nDigite uma das opções possíveis!");
					}
				}
				break;
			case 3: // LOGIN ADMINISTRADOR
				try {
					System.out.print("\nDigite o seu nome: ");
					String nomeDoAdm = sc.nextLine();
					System.out.print("Digite a chave-mestra: ");
					String senhaDoAdm = sc.nextLine();
					Administrador adm = new Administrador(nomeDoAdm, senhaDoAdm);
					System.out.printf("Bem-vindo %s!\n", adm.getNome());
					
					boolean admLogado = true;
					do {
						int escolha = inputNumero(sc, ec.getMenuAdm());
						
						if (escolha == 1){ // ADICIONAR CATEGORIA
							int quantCat = inputNumero(sc, "\nQuantas categorias deseja adicionar? ");
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja adicionar? ");
								String catNovoNome = sc.nextLine();
								Categoria cat = new Categoria();
								ec.editarCategoria(cat, catNovoNome);
								ec.addCategoria(cat);
							}
						} else if (escolha == 2){ // EDITAR CATEGORIA
							System.out.print("\n" + ec);
							int numberCat = inputNumero(sc, "\nQual categoria deseja editar? ");
							System.out.print("\nDigite o novo nome da categoria: ");
							String catName = sc.nextLine();
							Categoria cat = ec.retornaCategoria(numberCat);
							cat.setNome(catName);
						} else if (escolha == 3){ // REMOVER CATEGORIA
							int quantCat = inputNumero(sc, "\nQuantas categorias deseja remover? ");
							System.out.print("\n" + ec);
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja remover? ");
								String catName = sc.nextLine();
								ec.removerCategoria(catName);
							}
						} else if (escolha == 4){ // LISTAR CATEGORIAS
							System.out.print("\n" + ec);
						} else if (escolha == 5){ // EXIBIR USUÁRIOS
							int opcaoEscolhida = inputNumero(sc, "\nMENU ADMINISTRADOR\n1- Exibir clientes\n"
									+ "2- Exibir vendedores\nDigite uma das opções: ");
							if (opcaoEscolhida == 1) { // EXIBIR CLIENTES
								System.out.print("\nCLIENTES CADASTRADOS\n");
								ec.getClientes().forEach(System.out::println);
							} else if (opcaoEscolhida == 2) { // EXIBIR VENDEDORES
								System.out.print("\nVENDEDORES CADASTRADOS\n");
								ec.getVendedores().forEach(System.out::println);
							} else {
								System.out.print("\nDigite uma das opções possíveis!\n");
							}
						} else if (escolha == 6){ // SAIR
							admLogado = false;
						} else {
							System.out.print("\nDigite uma das opções possíveis!\n");
						}
					} while (admLogado);
				} catch (SenhaInvalidaException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4: // CRIAR CONTA NOVA
				int opcaoDeConta = inputNumero(sc, ec.getMenuDeContas());
				
				switch (opcaoDeConta) {
				case 1: // CRIAR CONTA DE CLIENTE
					System.out.print("\nDigite seu nome: ");
					String nomeCliente = sc.nextLine();
					System.out.print("Digite sua nova senha: ");
					String senhaCliente = sc.nextLine();
					System.out.print("Qual saldo inicial da sua conta? ");
					Double saldoCliente = Double.parseDouble(sc.nextLine());
					System.out.print("Digite seu endereço: ");
					String endCliente = sc.nextLine();
					System.out.print("Digite seu CPF: ");
					String cpfCliente = sc.nextLine();
					System.out.print("Digite sua chave PIX: ");
					String pixCliente = sc.nextLine();
					
					Cliente cliente = new Cliente(nomeCliente, senhaCliente, saldoCliente, endCliente, cpfCliente, pixCliente);
					ec.addCliente(cliente);
					break;
				case 2: // CRIAR CONTA DE VENDEDOR
					System.out.print("\nDigite seu nome: ");
					String nomeVend = sc.nextLine();
					System.out.print("Digite sua nova senha: ");
					String senhaVend = sc.nextLine();
					System.out.print("Qual saldo inicial da sua conta? ");
					Double saldoVend = Double.parseDouble(sc.nextLine());
					System.out.print("Digite seu endereço: ");
					String endVend = sc.nextLine();
					System.out.print("Digite seu CPF: ");
					String cpfVend = sc.nextLine();
					System.out.print("Digite sua chave PIX: ");
					String pixVend = sc.nextLine();
					
					Vendedor vendedor = new Vendedor(nomeVend, senhaVend, saldoVend, endVend, cpfVend, pixVend);
					ec.addVendedor(vendedor);
					break;
				case 3:
					break;
				default:
					System.out.print("\nDigite uma das opções possíveis!\n");
				}
			}
		} while (opcao != 5);
		
		System.out.print("\nDesconectado.");
	sc.close();
}
	
	public static int inputNumero(Scanner sc, String messagem) {
		System.out.print(messagem);
		boolean canGo = false;
		int theReturn = 0;
		
		while (!canGo) {
			try {
				theReturn = Integer.parseInt(sc.nextLine());
				canGo = true;
			} catch (NumberFormatException e) {
				System.out.print("Digite apenas opções númericas!");
				System.out.print("\n" + messagem);
			}
		} return theReturn;
	}
	
//	public static void salvaObjeto(Usuario user) {
//		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Users.ser"))) {
//		    out.writeObject(user);
//		    out.close();
//		    System.out.printf("Arquivo salvo com sucesso: Users.ser\n");
//		} catch(IOException e) {
//			e.printStackTrace();
//		}
//	}
//	
//	public static void lerObjeto() {
//		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users.ser"))) {
//	         Usuario user = (Usuario) in.readObject();
//	         in.close();
//	      } catch(IOException | ClassNotFoundException e) {
//	         e.printStackTrace();
//	      }
//	}
}