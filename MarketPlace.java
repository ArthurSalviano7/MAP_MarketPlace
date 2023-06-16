package marketplace;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import Fachada.Fachada;
import serializacao.Serializacao;

public class MarketPlace {
	
	static Scanner sc = new Scanner(System.in);
	static Fachada fachada = new Fachada();
	
	public static void main(String[] args) {
		Fachada.lerObjetos();
		menu();
		sc.close();
	
	}

	public static void menu() {
		System.out.println("MENU");
		System.out.println("1- Login");
		System.out.println("2- Cadastrar comprador");
		System.out.println("3- Cadastrar loja");
		System.out.println("");
		System.out.println("Insira uma opcao:");

		int comando = 0;
		
		try {
			comando = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		}catch(InputMismatchException exception) {
			System.out.println("Comando inválido");
			menu();
		}
		
		switch(comando) {
			case 1:
				Login();
			case 2:
				cadastrarComprador();
				break;
			case 3:
				cadastrarLoja();
				break; 
			default:
				System.out.println("Comando inválido");
				menu();
		}
	}
	
	private static void Login() {
		System.out.println("LOGIN");
		System.out.println("Insira o email:");
		String email = sc.nextLine();
		System.out.print("Insira a senha: ");
		String senha = sc.nextLine();
		
		if(fachada.autenticar(email, senha).equals("Comprador autenticado")) {
			System.out.println("Login realizado com sucesso!!");
			System.out.println();
			fachada.menuDoComprador();
		}
		else if(fachada.autenticar(email, senha).equals("Loja autenticada")) {
			System.out.println("Login realizado com sucesso!!");
			System.out.println();
			fachada.menuDaLoja();
		}else {
			System.out.println("-------------------");
			System.out.println("Autenticação falhou!!\n");
		}
		
		
		menu();
	}
	
	private static void cadastrarComprador() {
		System.out.print("Digite seu nome: ");
		String nome = sc.nextLine();
		System.out.print("Digite seu email: ");
		String email = sc.nextLine();
		System.out.print("Digite a senha desejada: ");
		String senha = sc.nextLine();
		System.out.print("Digite o seu cpf: ");
		String cpf	= sc.nextLine();
		System.out.print("Digite o seu endereco: ");
		String endereco = sc.nextLine();
		fachada.cadastrarComprador(nome, email, senha, cpf, endereco);
	}
    
	private static void cadastrarLoja() {
		System.out.print("Digite o nome da Loja: ");
		String nome = sc.nextLine();
		System.out.print("Digite email da Loja: ");
		String email = sc.nextLine();
		System.out.print("Digite a senha desejada: ");
		String senha = sc.nextLine();
		System.out.print("Digite o CNPJ: ");
		String cnpj	= sc.nextLine();
		System.out.print("Digite o CPF: ");
		String cpf	= sc.nextLine();
		System.out.print("Digite o seu endereco: ");
		String endereco = sc.nextLine();
		fachada.cadastrarLoja(nome, email, senha, cnpj, cpf, endereco);
	}

}
