package application;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//import java.io.ObjectOutputStream;
import java.util.Scanner;
import entities.Administrador;
import entities.Carrinho;
import entities.Categoria;
import entities.Cliente;
import entities.Ecommerce;
import entities.Produto;
//import entities.Usuario;
import entities.Vendedor;
import entities.exceptions.ObjetoNaoEncontradoException;
import entities.exceptions.SenhaInvalidaException;

public class EcommerceProgram {

	public static void main(String[] args) throws ObjetoNaoEncontradoException {
		
		Scanner sc = new Scanner(System.in);
		Ecommerce ec = new Ecommerce("Feira Ecommerce");
		
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
						int numEscolhido = inputNumero(sc, "\nMENU\n1- Comprar produto\n2- Ver carrinho"
								+ "\n3- Sair\nDigite uma das opções (1/3): ");
					
						if (numEscolhido == 1) {
							System.out.print("\n" + ec);
							int catEscolhida = inputNumero(sc, "\nEscolha uma das categorias: ");
							Categoria cat = ec.retornaCategoria(catEscolhida);
							System.out.print("\n" + cat);
							int prodEscolhido = inputNumero(sc, "\nQual produto deseja adicionar ao carrinho? ");
							Produto prod = cat.retornaProduto(prodEscolhido);
							Cliente cliente = ec.achaCliente(nomeDoCliente);
							cliente.addNoCarrinho(prod);
							System.out.println("\nProduto adicionado ao carrinho!");
						} else if (numEscolhido == 2) {
							Cliente cliente = ec.achaCliente(nomeDoCliente);
							System.out.print("\n" + cliente.verCarrinho());
						} else {
							clienteLogado = false;
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
				
				if (vendedorLogado) {
					int numEscolhido = inputNumero(sc, "\nMENU\n1- Adicionar produto\n2- Editar produto\nDigite a opção (1/2): ");
						
					if (numEscolhido == 1){ // ADICIONAR PRODUTO
						System.out.print("\n" + ec);
						int numCat = inputNumero(sc, "\nEm qual das categorias deseja adicionar? ");
						Categoria cat = ec.retornaCategoria(numCat);
						int quantProd = inputNumero(sc, "\nQuantos produtos deseja adicionar? ");
						System.out.println();
							
						for (int i = 0; i < quantProd; i++) {
							System.out.print("Qual nome do produto que deseja adicionar? ");
							String prodNovoNome = sc.nextLine();
							Produto product = new Produto();
							cat.editarProduto(product, prodNovoNome);
							cat.addProduto(product);
						}
						System.out.print("\n" + cat);
					} else if (numEscolhido == 2){ // EDITAR PRODUTO
						System.out.print("\n" + ec);
						int numCat = inputNumero(sc, "\nEm qual das categorias está o produto? ");
						Categoria cat = ec.retornaCategoria(numCat);
						System.out.print("\n" + cat);
						int numProd = inputNumero(sc, "\nQual produto deseja editar? ");
						System.out.print("\nDigite o novo nome do produto: ");
						String novoNomeProd = sc.nextLine();
						Produto prod = cat.retornaProduto(numProd);
						prod.setNome(novoNomeProd);
						
						System.out.print("\n" + cat);
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
					
					String option;
					do {
						int escolha = inputNumero(sc, ec.getMenuCRUD());
						
						if (escolha == 1) { // ADICIONAR CATEGORIA
							int quantCat = inputNumero(sc, "\nQuantas categorias deseja adicionar? ");
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja adicionar? ");
								String catNovoNome = sc.nextLine();
								Categoria cat = new Categoria();
								ec.editarCategoria(cat, catNovoNome);
								ec.addCategoria(cat);
							}
						} else if (escolha == 2) { // EDITAR CATEGORIA
							System.out.print("\n" + ec);
							int numberCat = inputNumero(sc, "\nQual categoria deseja editar? ");
							System.out.print("\nDigite o novo nome da categoria: ");
							String catName = sc.nextLine();
							Categoria cat = ec.retornaCategoria(numberCat);
							cat.setNome(catName);
						} else if (escolha == 3) { // REMOVER CATEGORIA
							int quantCat = inputNumero(sc, "\nQuantas categorias deseja remover? ");
							System.out.print("\n" + ec);
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja remover? ");
								String catName = sc.nextLine();
								ec.removerCategoria(catName);
							}
						} else { // LISTAR CATEGORIAS
							System.out.print("\n" + ec);
						}
						
						System.out.print("\nDeseja continuar? ");
						option = sc.nextLine().toLowerCase();
					} while (!option.equals("n"));
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
					Carrinho carrinho = new Carrinho(nomeCliente);
					cliente.associaCarrinho(carrinho);
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
				System.out.print("Apenas opções númericas!");
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