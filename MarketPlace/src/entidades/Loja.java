package entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Loja implements Serializable{
	
	private static int idProduto = 1;
	private String nome;
	private String email;
	private String senha;
	private String tipoUsuario;
	private String cnpj;
	private String cpf; 
	private String endereco;
	private double reputacao;
	private ArrayList<Produto> listaDeProdutos = new ArrayList<>();
	
	public Loja() {}
	
	public Loja(String nome, String email, String senha, String tipoUsuario, String cnpj, String cpf, String endereco,
			double reputacao, ArrayList<Produto> listaDeProdutos) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cnpj = cnpj;
		this.cpf = cpf;
		this.endereco = endereco;
		this.reputacao = reputacao;
		this.listaDeProdutos = listaDeProdutos;
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
	
	public ArrayList<Produto> getListaDeProdutos() {
		return listaDeProdutos;
	}

	public void setListaDeProdutos(ArrayList<Produto> listaDeProdutos) {
		this.listaDeProdutos = listaDeProdutos;
	}
	
	
	//CRUD Produtos
	public void cadastrar(Produto produto) {
		produto.setId(idProduto);
		idProduto++;
		listaDeProdutos.add(produto);
	}
	
	public String exibir(int id) {
		return listaDeProdutos.get(id - 1).toString();
	}
	
	public String buscar(String nomeBuscado) {
		String nomeDoProduto;
		for(int i = 0; i < listaDeProdutos.size(); i++){
			nomeDoProduto = listaDeProdutos.get(i).getDescricao().toLowerCase();
			
			if (nomeDoProduto.contains(nomeBuscado.toLowerCase())){
				Produto produto = listaDeProdutos.get(i);
				return produto.toString();
			}
		}
		return "Produto não encontrado na busca";
	}
	
	//Atualiza o produto ao passar o id original e o novo produto já atualizado
	public void atualizar(int id, Produto novoProduto) {
		
		for(int i = 0; i < listaDeProdutos.size(); i++){
			int idDoProduto = listaDeProdutos.get(i).getId();
			
			if (idDoProduto == id){
				listaDeProdutos.set(i, novoProduto);
				System.out.println("Produto atualizado");
			}
		}
	}
	
	public void remover(int id) {
		for(int i = 0; i < listaDeProdutos.size(); i++){
			int idDoProduto = listaDeProdutos.get(i).getId();
			
			if (idDoProduto == id){
				listaDeProdutos.remove(i);
				System.out.println("Produto removido");
			}
		}
	}
	
	public void listar() {
		System.out.println("Lista de Produtos: ");
		System.out.println("ID -> descricao ");

		for(Produto produto : listaDeProdutos) {
			System.out.println(produto.getId() + " -> " + produto.getDescricao());
		}
	}
}
