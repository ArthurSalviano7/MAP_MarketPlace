package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import Fachada.Fachada;

public class Comprador implements IFCrud<Comprador>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 30L;
	private int id;
	private String nome;
	private String email;
	private String senha;
	private String tipoUsuario;
	private String cpf; // pode ser um inteiro talvez
	private String endereco;
	private double pontuacao = 0;
	private Carrinho carrinho = new Carrinho();
	private ArrayList<Produto> listaHistorico = new ArrayList<>();
	
	public Comprador() {
		carrinho = new Carrinho(); // Quando criar um comprador um carrinho será associado a ele
	}
	
	public Comprador (int id, String nome,String email,String senha,String tipoUsuario,String cpf,String endereco) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cpf = cpf;
		this.endereco = endereco;
		
	}

	public Comprador (int id, String nome,String email,String senha,String tipoUsuario,String cpf,String endereco,double pontuacao) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cpf = cpf;
		this.endereco = endereco;
		this.pontuacao = pontuacao;
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
	
	public double getPontuacao() {
		return pontuacao;
	}
	
	public void setPontuacao(double pontuacao) {
		this.pontuacao = pontuacao;
	}

	public Carrinho getCarrinho(){
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho){
		this.carrinho = carrinho;
	}
	
	public String toString(){
        return "ID: " + id + "\n"
                + "Nome: " + nome + "\n"
                + "Email: " + email + "\n"
                + "Senha: " + senha + "\n"
                + "Tipo de Usuário: " + tipoUsuario + "\n"
                + "CPF: " + cpf + "\n"
                + "Endereço: " + endereco +"\n"
                + "Pontuação: " + pontuacao +"\n";
    }

	
	public void listarHistorico(){
		for(Produto produto : listaHistorico){
			System.out.println("Id : " + produto.getId());
			System.out.println("Valor : " + produto.getValor());
			System.out.println("Tipo : " + produto.getTipo());
			System.out.println("Marca : " + produto.getMarca());
			//Talvez um do dia da compra
			System.out.println("-----------------------");
		}

	}
	
	//CRUD Comprador:

		//Cadastra o próprio objeto Comprador na lista especificada
		public void cadastrar() {
			Fachada.listaCompradores.add(this); //A atualização do Id ocorre na classe Fachada
			System.out.println("Usuário Comprador cadastrado com sucesso");
		}

		//Exibe o Comprador pelo ID
		public String exibir(int id, ArrayList<Comprador> listaCompradores) { 

			for(int i = 0; i < listaCompradores.size(); i++){
				if (listaCompradores.get(i).getId() == id) {
					return listaCompradores.get(i).toString();
				}
			}

			return "Usuário Comprador não encontrado \n";
		}

		public String buscar(String nomeBuscado, ArrayList<Comprador> listaDeCompradores) {
			for(int i = 0; i < listaDeCompradores.size(); i++){
				String nomeDoComprador = listaDeCompradores.get(i).getNome().toLowerCase();

				if (nomeDoComprador.contains(nomeBuscado.toLowerCase())){
					Comprador comprador = listaDeCompradores.get(i);
					return comprador.toString();
				}
			}
			return "Usuário Comprador não foi encontrado na busca \n";
		}

		//Atualiza o Comprador o novo usuário já atualizada e a lista de compradores
		public String atualizar(Comprador novoComprador) {

			for(int i = 0; i < Fachada.listaCompradores.size(); i++){
				int idDoComprador = Fachada.listaCompradores.get(i).getId();

				if (idDoComprador == this.id){
					Fachada.listaCompradores.set(i, novoComprador);
					return "Usuário Comprador atualizado com sucesso \n";
				}
			}
			return "Usuário Comprador não encontrado\n";
		}

		public String remover() {
			for(int i = 0; i < Fachada.listaCompradores.size(); i++){
				int idDoComprador = Fachada.listaCompradores.get(i).getId();

				if (idDoComprador == this.id){
					Fachada.listaCompradores.remove(i);
					return "Usuário Comprador removido \n";
				}
			}
			return "Usuário Comprador não encontrado \n";
		}

		public void listar(ArrayList<Comprador> listaDeCompradores) {
			System.out.println("Lista de Compradores:");
			System.out.println("ID -> Nome do Usuário -> Email");
			
			for(Comprador comprador : listaDeCompradores) {
				System.out.println(comprador.getId() + " -> " + comprador.getNome() + " -> " + comprador.getEmail());
			}
		}

		public void adicionarProdutoHistorico(Produto produto){
			listaHistorico.add(produto);
		}

		public ArrayList<Produto> getListaHistorico(){
			return listaHistorico;
		}
}

	 

