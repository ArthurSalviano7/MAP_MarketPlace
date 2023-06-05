package Fachada;

import entidades.Comprador;
import entidades.Loja;
import entidades.Produto;
import marketplace.MarketPlace;
import serializacao.Serializacao;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Fachada {
    public static ArrayList<Comprador> listaCompradores = new ArrayList<>();
    public static ArrayList<Loja> listaLojas = new ArrayList<>();
    public static ArrayList<Produto> listaProdutos = new ArrayList<>();
	private static int id = 1; //Id para Lojas e compradores
	public static int idDoUsuarioAtual;
	public static ArrayList<Object> listaDeObjetos = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);
		
    public Fachada(){}
    
    public static void lerObjetos() {
    	listaDeObjetos = Serializacao.lerArquivo("arquivoObjetos");
    	
    	for (Object objeto : listaDeObjetos) {
    		
    		if(objeto instanceof Comprador) {
    			Comprador comprador = (Comprador) objeto;
    			listaCompradores.add(comprador);
    		}else if(objeto instanceof Loja) {
    			Loja loja = (Loja) objeto;
    			listaLojas.add(loja);
    		}
    	}  	
    }
    
    public static void gravarObjetos() {
    	
    	if(!listaDeObjetos.isEmpty()) { 
    		
    		for (Comprador comprador : listaCompradores) {
    		    listaDeObjetos.add(comprador);
    		}

    		for (Loja loja : listaLojas) {
    		    listaDeObjetos.add(loja);
    		}
    		
    		Serializacao.gravarArquivo(listaDeObjetos, "arquivoObjetos");
    	}
    }
    
    public static void menuDoComprador() {
    	lerObjetos(); //Ler o arquivo antes de realizar qualquer alteração
    	recuperarID();
    	
    	System.out.println("MENU COMPRADOR");
		System.out.println("1- Ver Produtos");
		System.out.println("2- Configurar Conta");
		System.out.println("3- Sair");
		System.out.println("");
		System.out.println("Insira uma opcao:");
		
		int comando = 0;
		
		try {
			comando = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		}catch(InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuDoComprador();
		}
		
		switch(comando) {
			case 1:
				listarProdutos();
				break;
			case 2:
				menuContaComprador();
				break; // revisar
			case 3:
				System.out.println("Sistema Encerrado!");
				MarketPlace.menu();
				break; // revisar
			default:
				System.out.println("Comando inválido");
				menuDoComprador();
		}
		
    }//Fim do metodo menuDoComprador()
    
    public static void menuDaLoja() {
    	lerObjetos();
    	recuperarID();
    	
    	
    	System.out.println("MENU LOJA");
		System.out.println("1- Cadastrar Produto");
		System.out.println("2- Exibir Produto");
		System.out.println("3- Atualizar Produto");
		System.out.println("4- Buscar Produto");
		System.out.println("5- Remover Produto");
		System.out.println("6- Listar Produtos");
		System.out.println("7- Configurar Conta");
		System.out.println("8- Sair");
		System.out.println("");
		System.out.println("Insira uma opcao:");
		
		int comando = 0;
		
		try {
			comando = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		}catch(InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuDaLoja();
		}
		
		Loja loja = new Loja();
		
		for(Loja lojaAux : listaLojas) {
			if(lojaAux.getId() == idDoUsuarioAtual) {
				loja = lojaAux;
			}
		}
		
		switch(comando) {
			case 1:
				System.out.println("Insira a descricao do produto: ");
				String descricao = sc.nextLine();
				System.out.println("Insira a quantidade do produto: ");
				int quantidade = sc.nextInt();
				sc.nextLine();
				System.out.println("Insira o valor do produto: ");
				double valor = sc.nextDouble();
				sc.nextLine();
				System.out.println("Insira o Tipo do produto: ");
				String tipo = sc.nextLine();
				System.out.println("Insira a marca do produto: ");
				String marca = sc.nextLine();
				
				Produto produto = new Produto(descricao, quantidade, valor, tipo, marca);
				produto.cadastrar();
				
				loja.setListaDeProdutos(listaProdutos);
				System.out.println("Produto Cadastrado!!");
				gravarObjetos();
				menuDaLoja();
				break;
			case 2:
				menuContaComprador();
				break; // revisar
			case 3:
				System.out.println("Sistema Encerrado!");
				System.exit(0);
				break; // revisar
			case 6:
				loja.listarProdutos();
				menuDaLoja();
				break; 
			default:
				System.out.println("Comando inválido");
				menuDoComprador();
	}
		
    }//Fim do metodo menuDaLoja()
    
	public static void menuContaComprador() {
		System.out.println("MENU COMPRADOR - CONFIGURAR CONTA");
		System.out.println("1- Exibir informações da conta");
		System.out.println("2- Mudar Nome");
		System.out.println("3- Mudar Email");
		System.out.println("4- Mudar Senha");
		System.out.println("5- Mudar Endereco");
		System.out.println("6- Excluir Conta");
		System.out.println("7- Sair");
		System.out.println("");
		System.out.println("Insira uma opcao:");
		
		int comando = 0;
		
		try {
			comando = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		}catch(InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuDaLoja();
		}
		
		Comprador comprador = new Comprador();
		
		for(Comprador comp : listaCompradores) {
			if(comp.getId() == idDoUsuarioAtual) {
				comprador = comp;
			}
		}
		
		switch(comando) {
			case 1:
				System.out.println(comprador.exibir(idDoUsuarioAtual, listaCompradores));
				menuContaComprador();
				break;
			case 2:
				System.out.print("Insira o novo nome desejado: ");
				String novoNome = sc.nextLine();
				
				comprador.setNome(novoNome);
				
				comprador.atualizar(comprador);
				gravarObjetos(); //Todas as alterações precisam ser gravadas no arquivo
				System.out.println("Nome atualizado!!");
				menuDoComprador();
				break; // Fim de Mudar Nome
			case 3:
				System.out.print("Insira o novo email desejado: ");
				String novoEmail = sc.nextLine();
				
				comprador.setEmail(novoEmail);
				
				comprador.atualizar(comprador);
				gravarObjetos();
				System.out.println("Email atualizado!!");
				menuDoComprador();
				break; // Fim de Mudar Email
			case 4:
				System.out.print("Insira a nova senha desejada: ");
				String novaSenha = sc.nextLine();
				
				comprador.setSenha(novaSenha);
				
				comprador.atualizar(comprador);
				gravarObjetos();
				System.out.println("Senha atualizada!!: " + comprador.toString());
				menuDoComprador();
				break; // Fim de Mudar Senha
			case 5:
				System.out.print("Insira o novo Endereco desejado: ");
				String novoEndereco = sc.nextLine();
				
				comprador.setEndereco(novoEndereco);
				
				comprador.atualizar(comprador);
				gravarObjetos();
				System.out.println("Endereco atualizado!!");
				menuDoComprador();
				break; // Fim de Mudar Endereco
			case 6:
				System.out.println("Tem certeza que deseja excluir a conta?");
				System.out.println("1- SIM\n2- NAO");
				int op = sc.nextInt();
				sc.nextLine(); // Consumir a linha pendente

				if (op == 1) {
					System.out.println(comprador.remover());
					gravarObjetos();
				}else if (op == 2) {
					menuContaComprador();
				}else {
					System.out.println("Comando inválido");
					menuContaComprador();
				}
				MarketPlace.menu();
				break; // Fim de Excluir
			case 7:
				menuDoComprador();
				break;
			default:
				System.out.println("Comando inválido");
				menuContaComprador();
		}
	}//Fim do menuContaComprador()

    
    public String autenticar(String email, String senha) {
    	
    	listaDeObjetos = Serializacao.lerArquivo("arquivoObjetos");
    	
    	for (Object objeto : listaDeObjetos) {
    		
    		if(objeto instanceof Comprador) {
    			Comprador comprador = (Comprador) objeto;
    			if(comprador.getEmail().equals(email) && comprador.getSenha().equals(senha)){
    				idDoUsuarioAtual = comprador.getId();
                    return "Comprador autenticado";
                }
    		}
    		
    		if(objeto instanceof Loja) {
    			Loja loja = (Loja) objeto;
    			if(loja.getEmail().equals(email) && loja.getSenha().equals(senha)){
    				idDoUsuarioAtual = loja.getId();
    				return "Loja autenticada";
    			}
    		}
    	}

        return "não autenticado";
    }//Fim do método autenticar()

    public static void listarCompradores(){
        for(Comprador comprador : listaCompradores){
            System.out.println("Nome : " + comprador.getNome());
            System.out.println("Email : " + comprador.getEmail());
            System.out.println("-------------------------");
        }
    }

    
    public static void listarProdutos() {
    	
    	if(listaLojas.isEmpty()) {
    		System.out.println("Ainda não há produtos cadastrados!");
    		menuDoComprador();
    	}else {
    		for(Loja loja : listaLojas) {
	    		Produto produto = new Produto();
	    		produto.listar(loja.getListaDeProdutos()); //Lista todos os produtos de cada Loja
    		}
    		menuDoComprador();
    	}
    	
    }
    
    //Método para recuperar o Id de acordo com os objetos do arquivo
    public static int recuperarID() {
		if (!listaDeObjetos.isEmpty()) { //Se a lista não estiver vazia
			Object ultimoObjeto = listaDeObjetos.get(listaDeObjetos.size() - 1);
			if (ultimoObjeto instanceof Loja) {
				Loja ultimaLoja = (Loja) ultimoObjeto;
				id = ultimaLoja.getId() + 1;
				return id;
			} else if (ultimoObjeto instanceof Comprador) {
				Comprador ultimoComprador = (Comprador) ultimoObjeto;
				 id = ultimoComprador.getId() + 1;
				 return id;
			}
		}
		id = 1;
		return id;
	}//Fim do método recuperarID
    
    public static void cadastrarComprador(String nome, String email, String senha, String cpf, String endereco) {
    	Comprador comprador = new Comprador(id, nome, email, senha, "Comprador", cpf, endereco);
    	comprador.cadastrar();
    	lerObjetos();
    	listaDeObjetos.add(comprador);
    	
    	idDoUsuarioAtual = id;
    	id++; //Atualiza o id para os objetos
    	gravarObjetos(); //Grava o objeto no arquivo logo após o cadastro
    	
    	menuDoComprador();
    }

    public static void cadastrarLoja(String nome, String email, String senha, String cnpj, String cpf, String endereco) {
        Loja loja = new Loja(id, nome, email, senha,"Loja", cnpj, cpf, endereco);
        loja.cadastrar();
    	lerObjetos();
    	listaDeObjetos.add(loja);
        
        idDoUsuarioAtual = id;
    	id++; //Atualiza o id para os objetos
    	gravarObjetos(); //Grava o objeto no arquivo logo após o cadastro
    	
    	menuDaLoja();
    }
    
 }