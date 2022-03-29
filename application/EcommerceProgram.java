package application;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;
import entities.Administrador;
import entities.Categoria;
import entities.Cliente;
import entities.Ecommerce;
import entities.Usuario;
import entities.exceptions.SenhaInvalidaException;

public class EcommerceProgram {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		Ecommerce ec = new Ecommerce("Feira Ecommerce");
		
		/*
		boolean admLogado = false;
		
		do {
			try {
				String nomeDoAdm = sc.nextLine();
				String senhaDoAdm = sc.nextLine();
				Administrador adm = new Administrador(nomeDoAdm, senhaDoAdm);
				admLogado = true;
				System.out.printf("Bem-vindo %s!\n", adm.getNome());
			} catch (SenhaInvalidaException e) {
				System.out.print(e.getMessage() + "\n");
			}
		} while (!admLogado);

		
		String op;
		do {
			System.out.println("\nQual categoria deseja adicionar? ");
			String catName = sc.nextLine();
			Categoria cat = new Categoria();
			cat.setNome(catName);
			ec.addCategoria(cat);
			
			System.out.println("\nQuer adicionar mais categorias? ");
			op = sc.nextLine();
		} while (!op.equals("n"));
		
		System.out.println(ec);
		
		String yOrNot;
		do {
			System.out.print("\nDeseja adicionar produtos? ");
			yOrNot = sc.nextLine();
			
			if (yOrNot.equals("s")) {
				System.out.println("\n" + ec);
				System.out.print("\nEm qual das categorias deseja adicionar: ");
				int opcao = Integer.parseInt(sc.nextLine());
				System.out.print("\nQual produto deseja adicionar? ");
				String prodName = sc.nextLine();
				System.out.print("\nQuantas unidades há disponíveis? ");
				int prodQuant = Integer.parseInt(sc.nextLine());
				Produto prod = new Produto(prodName, prodQuant);
				Categoria cat = ec.retornaCategoria(opcao);
				cat.addProduto(prod);
				
				System.out.print("\n" + cat);
			}
		} while (!yOrNot.equals("n"));
*/	
		int opcao;
		System.out.printf("Bem vindo ao %s!\n", ec.getNome());
		
		do {
			System.out.print("\n" + ec.getMenu());
			opcao = Integer.parseInt(sc.nextLine());
		
			switch (opcao) {
			case 1:
				break;	
			case 2:
				break;
			case 3:
				try {
					System.out.print("\nDigite o seu nome: ");
					String nomeDoAdm = sc.nextLine();
					System.out.print("Digite a chave-mestra: ");
					String senhaDoAdm = sc.nextLine();
					Administrador adm = new Administrador(nomeDoAdm, senhaDoAdm);
					System.out.printf("Bem-vindo %s!\n", adm.getNome());
					
					String option;
					do {
						System.out.print(ec.getMenuCRUD());
						int escolha = Integer.parseInt(sc.nextLine());
						
						if (escolha == 1){
							System.out.print("\nQuantas categorias deseja adicionar? ");
							int quantCat = Integer.parseInt(sc.nextLine());
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja adicionar? ");
								String catNovoNome = sc.nextLine();
								Categoria cat = new Categoria();
								ec.editarCategoria(cat, catNovoNome);
								ec.addCategoria(cat);
							}
						} else if (escolha == 2){
							System.out.print("\n" + ec);
							System.out.print("\nQual categoria deseja editar? ");
							int numberCat = Integer.parseInt(sc.nextLine());
							System.out.print("\nDigite o novo nome da categoria: ");
							String catName = sc.nextLine();
							Categoria cat = ec.retornaCategoria(numberCat);
							cat.setNome(catName);
						} else if (escolha == 3) {
							System.out.print("\nQuantas categorias deseja remover? ");
							int quantCat = Integer.parseInt(sc.nextLine());
							System.out.print("\n" + ec);
							System.out.println();
							
							for (int i = 0; i < quantCat; i++) {
								System.out.print("Qual nome da categoria que deseja remover? ");
								String catName = sc.nextLine();
								ec.removerCategoria(catName);
							}
						} else {
							System.out.print("\n" + ec);
						}
						
						System.out.print("\nDeseja continuar? ");
						option = sc.nextLine().toLowerCase();
					} while (!option.equals("n"));
				} catch (SenhaInvalidaException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 4:
				System.out.print(ec.getMenuDeContas());
				int opcaoDeConta = Integer.parseInt(sc.nextLine());
				
				switch (opcaoDeConta) {
				case 1:
					System.out.print("\nDigite seu nome: ");
					String nomeCliente = sc.nextLine();
					System.out.print("Digite sua nova senha: ");
					String senhaCliente = sc.nextLine();
					System.out.print("Qual saldo inicial da sua conta? ");
					Double saldoCliente = sc.nextDouble();
					System.out.print("Digite seu endereço: ");
					String enCliente = sc.nextLine();
					String endCliente = sc.nextLine();
					
					Usuario cliente = new Cliente(nomeCliente, senhaCliente, saldoCliente, endCliente);
					salvaObjeto(cliente);
					lerObjeto();
					break;
				case 2:
					
					break;
				}
				
//				try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/home/harlon/eclipse-workspace/Temp/Eclipse_Java/projetao/User.ser"))) {
//				    out.writeObject(cliente);
//				    out.close();
//				    System.out.printf("Arquivo salvo com sucesso na pasta: /tmp/Users.ser");
//				} catch(IOException e) {
//					e.printStackTrace();
//				}
			}
		} while (opcao != 5);
		
		System.out.println("\nDesconectado.");
	sc.close();
}
	
	public static void salvaObjeto(Usuario user) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("Users.ser"))) {
		    out.writeObject(user);
		    out.close();
		    System.out.printf("Arquivo salvo com sucesso: Users.ser\n");
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void lerObjeto() {
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("Users.ser"))) {
	         Usuario user = (Usuario) in.readObject();
	         in.close();
	      } catch(IOException | ClassNotFoundException e) {
	         e.printStackTrace();
	}
	}
}