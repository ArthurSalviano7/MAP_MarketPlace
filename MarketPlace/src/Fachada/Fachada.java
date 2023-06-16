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
	private static int id = 1; // Id para Lojas e compradores
	public static int idDoUsuarioAtual;
	public static ArrayList<Object> listaDeObjetos = new ArrayList<>();
	static Scanner sc = new Scanner(System.in);

	public Fachada() {
	}

	public static void lerObjetos() {
		listaDeObjetos = Serializacao.lerArquivo("arquivoObjetos");

		for (Object objeto : listaDeObjetos) {

			if (objeto instanceof Comprador) {
				Comprador comprador = (Comprador) objeto;
				listaCompradores.add(comprador);
			} else if (objeto instanceof Loja) {
				Loja loja = (Loja) objeto;
				listaLojas.add(loja);
			}
		}
	}

	public static void gravarObjetos() {

		if (listaDeObjetos.isEmpty()) {

			for (Comprador comprador : listaCompradores) {
				listaDeObjetos.add(comprador);
			}

			for (Loja loja : listaLojas) {
				listaDeObjetos.add(loja);
			}

		}
		Serializacao.gravarArquivo(listaDeObjetos, "arquivoObjetos");

	}

	public static void menuDoComprador() {
		recuperarID();

		System.out.println("MENU COMPRADOR");
		System.out.println("1- Ver Produtos");
		System.out.println("2- Configurar Conta");
		System.out.println("3- Sair");
		System.out.println("");
		System.out.println("Insira uma opcao:");

		int comandoComprador = 0;

		try {
			comandoComprador = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		} catch (InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuDoComprador();
		}

		switch (comandoComprador) {
		case 1:
			listarProdutos();
			break;
		case 2:
			menuContaComprador();
			break;
		case 3:
			System.out.println("Logout efetuado !!");
			MarketPlace.menu();
			break;
		default:
			System.out.println("Comando inválido");
			menuDoComprador();
		}

	}// Fim do metodo menuDoComprador()

	public static void menuDaLoja() {
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

		int comandoLoja = 0;

		try {
			comandoLoja = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente
		} catch (InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuDaLoja();
		}

		// Criar Loja para realizar as operações
		Loja loja = listaLojas.stream().filter((l) -> l.getId() == idDoUsuarioAtual).findFirst().get();

		Produto auxProduto = new Produto();
		int idProduto = 0;

		switch (comandoLoja) {
		case 1:
			// Cadastrar Produto:
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
			produto.setIdLoja(loja.getId());
			produto.cadastrar();

			ArrayList<Produto> produtosLoja = loja.getListaDeProdutos();
			produtosLoja.add(produto);
			loja.setListaDeProdutos(produtosLoja);
			gravarObjetos();
			menuDaLoja();
			break; // Fim de Cadastrar Produto
		case 2:
			// Exibir Produto:
			System.out.print("Insira o Id do produto a ser exibido: ");
			try {
				idProduto = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException exception) {
				System.out.println("ID inválido!");
				menuDaLoja();
			}

			System.out.println(auxProduto.exibir(idProduto, loja.getListaDeProdutos()));
			menuDaLoja();
			break; // Fim de Exibir Produto
		case 3:
			// Atualizar Produto:
			loja.listarProdutos();
			System.out.println("Informe o Id do produto que deseja atualizar");
			try {
				idProduto = sc.nextInt();
				sc.nextLine();
			} catch (InputMismatchException exception) {
				System.out.println("ID inválido!");
				menuDaLoja();
			}

			// Se o produto não está cadastrado na loja volta para o menu
			if (auxProduto.exibir(idProduto, loja.getListaDeProdutos()) == "Produto não encontrado\n") {
				System.out.println("Produto não encontrado na loja!");
				menuDaLoja();
			} else {
				// Atualizar Produto:
				// Copia as informações do produto em questão para realizar as alterações
				listaProdutos = loja.getListaDeProdutos();

				for (Produto prod : loja.getListaDeProdutos()) {
					if (prod.getId() == idProduto) {
						auxProduto = prod;
					}
				}

				System.out.println("Qual informação do produto deseja atualizar: ");
				System.out.println("1- Descricao");
				System.out.println("2- Quantidade");
				System.out.println("3- Valor");
				System.out.println("4- Tipo de produto");
				System.out.println("5- Marca");

				int comandoProduto = 0;
				try {
					comandoProduto = sc.nextInt();
					sc.nextLine();
				} catch (InputMismatchException exception) {
					System.out.println("comando inválido!");
					menuDaLoja();
				}

				switch (comandoProduto) {
				case 1:
					System.out.println("Insira a nova descricao: ");
					String novaDescricao = sc.nextLine();
					auxProduto.setDescricao(novaDescricao);

					System.out.println(auxProduto.atualizar(auxProduto));
					loja.setListaDeProdutos(listaProdutos);
					gravarObjetos();
					menuDaLoja();
					break;
				case 2:
					System.out.println("Insira a nova quantidade: ");
					int novaQuantidade = sc.nextInt();
					auxProduto.setQuantidade(novaQuantidade);

					System.out.println(auxProduto.atualizar(auxProduto));
					loja.setListaDeProdutos(listaProdutos);
					gravarObjetos();
					menuDaLoja();
					break;
				case 3:
					System.out.println("Insira o novo valor: ");
					double novoValor = sc.nextDouble();
					auxProduto.setValor(novoValor);

					System.out.println(auxProduto.atualizar(auxProduto));
					loja.setListaDeProdutos(listaProdutos);
					gravarObjetos();
					menuDaLoja();
					break;
				case 4:
					System.out.println("Insira o novo tipo para o produto: ");
					String novoTipo = sc.nextLine();
					auxProduto.setTipo(novoTipo);

					System.out.println(auxProduto.atualizar(auxProduto));
					loja.setListaDeProdutos(listaProdutos);
					gravarObjetos();
					menuDaLoja();
					break;
				case 5:
					System.out.println("Insira a nova marca para o produto: ");
					String novaMarca = sc.nextLine();
					auxProduto.setTipo(novaMarca);

					System.out.println(auxProduto.atualizar(auxProduto));
					loja.setListaDeProdutos(listaProdutos);
					gravarObjetos();
					menuDaLoja();
					break;
				default:
					System.out.println("Comando inválido!");
					menuDaLoja();
				}// Fim do switch informações do produto
			}
			menuDaLoja();
			break;// Fim de Atualizar Produto

		case 4:
			// Buscar Produto:
			System.out.println("Insira o nome do produto que deseja buscar: ");
			String nomeBuscado = sc.nextLine();

			System.out.println(auxProduto.buscar(nomeBuscado, loja.getListaDeProdutos()));
			menuDaLoja();
			break; // Fim de Buscar Produto
		case 5:
			// Remover Produto:
			listaProdutos = loja.getListaDeProdutos();
			loja.listarProdutos();

			System.out.println("Insira o id do produto que deseja remover: ");
			int idRemover = sc.nextInt();
			sc.nextLine();

			auxProduto.setId(idRemover);

			System.out.println(auxProduto.remover());
			menuDaLoja();
			break; // Fim de Remover Produto
		case 6:
			// Listar Produtos:
			loja.listarProdutos();
			menuDaLoja();
			break; // Fim de listar produtos
		case 7:
			// Configurar Conta:
			menuContaLoja();
			menuDaLoja();
			break;
		case 8:
			// Sair:
			System.out.println("Logout efetuado!");
			MarketPlace.menu();
			break;
		default:
			System.out.println("Comando inválido");
			menuDaLoja();
			break;
		}

	}// Fim do metodo menuDaLoja()

	public static void menuContaLoja() {
		recuperarID();

		System.out.println("MENU LOJA - CONFIGURAR CONTA");
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
		} catch (InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuContaLoja();
		}

		Loja loja = new Loja();

		for (Loja auxLoja : listaLojas) {
			if (auxLoja.getId() == idDoUsuarioAtual) {
				loja = auxLoja;
			}
		}

		switch (comando) {
		case 1:
			// Exibir informaçoes:
			System.out.println(loja.exibir(idDoUsuarioAtual, listaLojas));
			menuContaLoja();
			break;
		case 2:
			// Mudar nome:
			System.out.print("Insira o novo nome desejado: ");
			String novoNome = sc.nextLine();

			loja.setNome(novoNome);

			loja.atualizar(loja);
			gravarObjetos(); // Todas as alterações precisam ser gravadas no arquivo
			System.out.println("Nome atualizado!!");
			menuContaLoja();
			break; // Fim de Mudar Nome
		case 3:
			// Mudar email:
			System.out.print("Insira o novo email desejado: ");
			String novoEmail = sc.nextLine();
			// VerificarEmail(novoEmail);

			loja.setEmail(novoEmail);

			loja.atualizar(loja);
			gravarObjetos();
			System.out.println("Email atualizado!!");
			menuContaLoja();
			break; // Fim de Mudar Email
		case 4:
			// Mudar senha:
			System.out.print("Insira a nova senha desejada: ");
			String novaSenha = sc.nextLine();

			loja.setSenha(novaSenha);

			loja.atualizar(loja);
			gravarObjetos();
			System.out.println("Senha atualizada!!");
			menuContaLoja();
			break; // Fim de Mudar Senha
		case 5:
			// Mudar Endereço:
			System.out.print("Insira o novo Endereco desejado: ");
			String novoEndereco = sc.nextLine();

			loja.setEndereco(novoEndereco);

			loja.atualizar(loja);
			gravarObjetos();
			System.out.println("Endereco atualizado!!");
			menuContaLoja();
			break; // Fim de Mudar Endereco
		case 6:
			// Excluir Conta:
			System.out.println("Tem certeza que deseja excluir a conta?");
			System.out.println("1- SIM\n2- NAO");
			int op = sc.nextInt();
			sc.nextLine(); // Consumir a linha pendente

			if (op == 1) {
				System.out.println(loja.remover());
				gravarObjetos();
			} else if (op == 2) {
				menuContaLoja();
			} else {
				System.out.println("Comando inválido");
				menuContaLoja();
			}
			MarketPlace.menu();
			break; // Fim de Excluir
		case 7:
			menuDaLoja();
			break;
		default:
			System.out.println("Comando inválido");
			menuContaLoja();
		}
	}// Fim do menuContaLoja()

	public static void menuContaComprador() {
		recuperarID();

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
		} catch (InputMismatchException exception) {
			System.out.println("Comando inválido");
			menuContaComprador();
		}

		Comprador comprador = new Comprador();

		for (Comprador comp : listaCompradores) {
			if (comp.getId() == idDoUsuarioAtual) {
				comprador = comp;
			}
		}

		switch (comando) {
		case 1:
			System.out.println(comprador.exibir(idDoUsuarioAtual, listaCompradores));
			menuContaComprador();
			break;
		case 2:
			System.out.print("Insira o novo nome desejado: ");
			String novoNome = sc.nextLine();

			comprador.setNome(novoNome);

			comprador.atualizar(comprador);
			gravarObjetos(); // Todas as alterações precisam ser gravadas no arquivo
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
			System.out.println("Senha atualizada!!");
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
			} else if (op == 2) {
				menuContaComprador();
			} else {
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
	}// Fim do menuContaComprador()

	public String autenticar(String email, String senha) {

		for (Object objeto : listaDeObjetos) {

			if (objeto instanceof Comprador) {
				Comprador comprador = (Comprador) objeto;
				if (comprador.getEmail().equals(email) && comprador.getSenha().equals(senha)) {
					idDoUsuarioAtual = comprador.getId();
					return "Comprador autenticado";
				}
			}

			if (objeto instanceof Loja) {
				Loja loja = (Loja) objeto;
				if (loja.getEmail().equals(email) && loja.getSenha().equals(senha)) {
					idDoUsuarioAtual = loja.getId();
					return "Loja autenticada";
				}
			}
		}

		return "não autenticado";
	}// Fim do método autenticar()

	public static void listarCompradores() {
		for (Comprador comprador : listaCompradores) {
			System.out.println("Nome : " + comprador.getNome());
			System.out.println("Email : " + comprador.getEmail());
			System.out.println("-------------------------");
		}
	}

	public static Loja getLojaPorId(int id) {
    for (Loja loja : listaLojas) {
        if (loja.getId() == id) {
            return loja;
        }
    }
    return null; // Retorna null se nenhuma loja for encontrada com o ID fornecido
}	
	
	public static void listarProdutos() {
    if (listaLojas.isEmpty()) {
        System.out.println("Ainda não há lojas cadastradas!");
        menuDoComprador();
    } else {
        for (Loja loja : listaLojas) {
            System.out.println("\nLoja " + loja.getId() + " - Lista de Produtos" + ": \n");
            List<Produto> produtosDaLoja = loja.getListaDeProdutos();
            if (produtosDaLoja.isEmpty()) {
                System.out.println("Não há produtos cadastrados nesta loja!");
            } else {
                for (Produto produto : produtosDaLoja) {
                    System.out.println(produto.toString()); // Exibir as informações de cada produto
                }
            }
        }
        menuDoComprador();
    }
   }


	/* 
	public static void listarProdutos() {

		if (listaLojas.isEmpty()) {
			System.out.println("Ainda não há produtos cadastrados!");
			menuDoComprador();
		} else {
			for (Loja loja : listaLojas) {
				Produto produto = new Produto();
				System.out.println("\nLoja " + loja.getId() + " - Lista de Produtos" + ": \n");
				produto.listar(loja.getListaDeProdutos()); // Lista todos os produtos de cada Loja
			}
			menuDoComprador();
		}
	}
	*/

	// Método para recuperar o Id de acordo com os objetos do arquivo
	public static int recuperarID() {
		if (!listaDeObjetos.isEmpty()) { // Se a lista não estiver vazia
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
	}// Fim do método recuperarID

	public static void cadastrarComprador(String nome, String email, String senha, String cpf, String endereco) {
		Comprador comprador = new Comprador(id, nome, email, senha, "Comprador", cpf, endereco);
		comprador.cadastrar();
		lerObjetos();
		listaDeObjetos.add(comprador);

		idDoUsuarioAtual = id;
		id++; // Atualiza o id para os objetos
		gravarObjetos(); // Grava o objeto no arquivo logo após o cadastro

		menuDoComprador();
	}

	public static void cadastrarLoja(String nome, String email, String senha, String cnpj, String cpf,
			String endereco) {
		Loja loja = new Loja(id, nome, email, senha, "Loja", cnpj, cpf, endereco);
		loja.cadastrar();
		listaDeObjetos.add(loja);

		idDoUsuarioAtual = id;
		id++; // Atualiza o id para os objetos
		gravarObjetos(); // Grava o objeto no arquivo logo após o cadastro

		menuDaLoja();
	}

}