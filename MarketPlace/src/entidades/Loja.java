package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import Fachada.Fachada;

public class Loja implements IFCrud<Loja>, Serializable{
	
	private static final long serialVersionUID = 20L;
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String tipoUsuario;
	private String cnpj;
	private String cpf; 
	private String endereco;
	private double reputacao;
	private String conceito; //Avaliação da loja(ruim, medio, bom, excelente)
	private ArrayList<Produto> listaDeProdutos = new ArrayList<>();
	private int totalAvaliacoes;
	private int somaAvaliacoes;
	
	public Loja() {}
	
	public Loja(int id, String nome, String email, String senha, String tipoUsuario, String cnpj, String cpf, String endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.endereco = endereco;
		this.totalAvaliacoes = 0; //edit
		this.somaAvaliacoes = 0; //edit
		this.reputacao = calcularReputacao();
    	this.conceito = definirConceito();
	}
	
	public Loja(int id, String nome, String email, String senha, String tipoUsuario, String cnpj, String cpf, String endereco,
			double reputacao, String conceito) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.endereco = endereco;
		//this.totalAvaliacoes = 0;
		//this.somaAvaliacoes = 0;
		//this.reputacao = 0;
		//this.conceito = "";
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public double getReputacao() {
		return reputacao;
	}

	public void setReputacao(double reputacao) {
		this.reputacao = reputacao;
	}
	
	public String getConceito() {
		return conceito;
	}

	public void setConceito(String conceito) {
		this.conceito = conceito;
	}
	
	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(ArrayList<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	
	
	
	//CRUD Loja:

	//Cadastra o próprio objeto Loja na lista especificada
	public void cadastrar() {
		Fachada.listaLojas.add(this); //A atualização do Id ocorre na classe Fachada
		System.out.println("Loja cadastrada com sucesso");
	}

	//Exibe a Loja pelo ID
	public String exibir(int id, ArrayList<Loja> listaDeLojas) { 

		for(int i = 0; i < listaDeLojas.size(); i++){
			if (listaDeLojas.get(i).getId() == id) {
				return listaDeLojas.get(i).toString();
			}
		}

		return "Loja não encontrada \n";
	}

	public String buscar(String nomeBuscado, ArrayList<Loja> listaDeLojas) {
		for(int i = 0; i < listaDeLojas.size(); i++){
			String nomeDaLoja = listaDeLojas.get(i).getNome().toLowerCase();

			if (nomeDaLoja.contains(nomeBuscado.toLowerCase())){
				Loja loja = listaDeLojas.get(i);
				return loja.toString();
			}
		}
		return "Loja não encontrada na busca \n";
	}

	//Atualiza a loja através do id original, a nova loja já atualizada e a lista de lojas
	public String atualizar(Loja novaLoja) {

		for(int i = 0; i < Fachada.listaLojas.size(); i++){
			int idDaLoja = Fachada.listaLojas.get(i).getId();

			if (idDaLoja == this.id){
				Fachada.listaLojas.set(i, novaLoja);
				return "Loja atualizada com sucesso \n";
			}
		}
		return "Loja não encontrada\n";
	}
	
	
	public String remover() {
	    for (int i = 0; i < Fachada.listaLojas.size(); i++) {
	        int idDaLoja = Fachada.listaLojas.get(i).getId();

	        if (idDaLoja == this.id) {
	            Fachada.listaLojas.remove(i);
	            Fachada.listaDeObjetos.remove(this); // Remover o objeto da lista de objetos
	            return "Loja removida \n";
	        }
	    }
	    return "Loja n�o encontrada \n";
	}
	
	public void listar(ArrayList<Loja> listaDeLojas) {
		System.out.println("Lista de Lojas: \n");
		System.out.println("ID -> Nome da Loja -> CNPJ");
		
		for(Loja loja : listaDeLojas) {
			System.out.println(loja.getId() + " -> " + loja.getNome() + " -> " + loja.getCnpj());
		}
	}
	
	public void listarProdutos() {
		System.out.println("Lista de Produtos: \n");
		System.out.println("ID  ->  Descricao  ->  Valor  ->  Quantidade  ->  Tipo  ->  Marca");		
		for(Produto produto : this.getListaDeProdutos()) {
        	System.out.println(produto.getId() + " -> " + produto.getDescricao() + " -> R$" + produto.getValor() + " -> " + produto.getQuantidade() + " -> " + produto.getTipo() + " -> " + produto.getMarca());
		}
	}

	//Atualiza os atributos
	public void adicionarAvaliacao(int avaliacao) {
    	totalAvaliacoes++;
    	somaAvaliacoes += avaliacao;
		this.reputacao = calcularReputacao();
		this.conceito = definirConceito();
    	
	}

	//Atualiza o valor da reputação
	private double calcularReputacao() {
    if (totalAvaliacoes == 0) {
        return 0.0;
    } else {
        return (double) somaAvaliacoes / totalAvaliacoes;
    	}
	}

	private String definirConceito() {
    	if (this.reputacao >= 4.5) {
        	return "Excelente";
    	} else if (this.reputacao >= 3.5) {
       	 	return "Bom";
    	} else if (this.reputacao >= 2.5) {
        	return "Médio";
    	} else {
        	return "Ruim";
    	}
	}

	public String toString(){
        return "ID: " + id + "\n"
                + "Nome: " + nome + "\n"
                + "Email: " + email + "\n"
                + "Senha: " + senha + "\n"
                + "Tipo de Usuário: " + tipoUsuario + "\n"
                + "CPF: " + cnpj + "\n"
                + "Endereço: " + endereco + "\n"
				+ "Reputação: " + reputacao + "\n"
				+ "Conceito: " + conceito;
    }


}
