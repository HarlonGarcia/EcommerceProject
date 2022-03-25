package application;
import java.util.Scanner;
import entities.Administrador;
import entities.Categoria;
import entities.Ecommerce;
import entities.Produto;
import entities.Subcategoria;
import entities.exceptions.SenhaInvalidaException;

public class EcommerceProgram {

	public static void main(String[] args) {
	
		
		Scanner sc = new Scanner(System.in);
		Ecommerce ec = new Ecommerce("Feira Tech");


		boolean admLogado = false;
		
		do {
			try {
				String nomeDoAdm = sc.nextLine();
				String senhaDoAdm = sc.nextLine();
				Administrador adm = new Administrador(nomeDoAdm, senhaDoAdm);
				admLogado = true;
				System.out.printf("Bem-vindo %s!\n", adm.getNome());
			} catch (SenhaInvalidaException e) {
				System.out.println(e.getMessage() + "\n");
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
			System.out.print("\nDeseja adicionar subcategoria? ");
			yOrNot = sc.nextLine();
			
			if (yOrNot.equals("s")) {
				System.out.println("\n" + ec);
				System.out.print("\nEm qual das categorias deseja adicionar? ");
				int opcao = Integer.parseInt(sc.nextLine());
				System.out.println("\nQual subcategoria deseja adicionar? ");
				String subName = sc.nextLine();
				Subcategoria sub = new Subcategoria(subName);
				Categoria cat = ec.retornaCategoria(opcao);
				cat.addSubcategoria(sub);
				
				System.out.println("\n" + cat);
			}
		} while (!yOrNot.equals("n"));

		System.out.print(ec.getMenu());
		
		int opcao = Integer.parseInt(sc.nextLine());
		
		while (opcao != 5) {
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
				} catch (SenhaInvalidaException e) {
					System.out.println(e.getMessage() + "\n");
				}
				break;
			case 4:
				System.out.print(ec.getMenuDeContas());
				int opcaoDeConta = Integer.parseInt(sc.nextLine());
				
				switch (opcaoDeConta) {
				case 1:
					break;
				case 2:
					break;
				}
			}
			
			System.out.println("\n" + ec.getMenu());
			opcao = Integer.parseInt(sc.nextLine());
		}
		
		System.out.println("Desconectado.");
	sc.close();
}
}