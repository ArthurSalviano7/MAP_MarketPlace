import java.util.ArrayList;
import java.util.Scanner;

import Fachada.Fachada;
import serializacao.Serializacao;

public class MarketPlace {
	
	public static void main(String[] args) {
		
		Fachada fachada = new Fachada();
		Scanner sc = new Scanner(System.in);
		
		menu(fachada, sc);
		sc.close();
	
	}

	private static void menu(Fachada fachada, Scanner sc) {
		System.out.println("MENU");
		System.out.println("1- Cadastrar comprador");
		System.out.println("2- Cadastrar loja");
		
		cadastrarComprador();
		cadastrarLoja();
	}
	
	private static void cadastrarComprador() {
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
		fachada.cadastrarLoja(nome, email, senha, cnpj, endereco);
		fachada.listarLoja();
	}

}
