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
		
		
		menu(fachada, sc);
		sc.close();
	
	}

	private static void menu(Fachada fachada, Scanner sc) {
		System.out.println("MENU");
		System.out.println("1- Login");
		System.out.println("2- Cadastrar comprador");
		System.out.println("2- Cadastrar loja");

		int comando = 0;
		
		try {
			comando = sc.nextInt();
		}catch(InputMismatchException exception) {
			System.out.println("Comando inválido");
		}
		
		switch(comando) {
			case 2:
				cadastrarComprador();
		}
		cadastrarComprador();
		cadastrarLoja();
	}
	
	private static void cadastrarComprador() {
		System.out.print("Digite seu nome: ");
		String nome = sc.nextLine();
		String email = sc.nextLine();
		String senha = sc.nextLine();
		String cpf	= sc.nextLine();
		String endereco = sc.nextLine();
		fachada.cadastrarComprador(nome, email, senha, cpf, endereco);
		fachada.listarCompradores();
	}
    
	private static void cadastrarLoja() {
		String nome = sc.nextLine();
		String email = sc.nextLine();
		String senha = sc.nextLine();
		String cnpj	= sc.nextLine();
		String endereco = sc.nextLine();
		//fachada.cadastrarLojas(nome, email, senha, cnpj, endereco);
		fachada.listarLojas();
	}

}
