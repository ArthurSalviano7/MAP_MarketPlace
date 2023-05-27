package entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class Comprador implements Serializable{
	
	private String nome;
	private String email;
	private String senha;
	private String tipoUsuario;
	private String cpf; // pode ser um inteiro talvez
	private String endereco;
	private double pontuacao;
	private ArrayList<Produto> listaHistorico = new ArrayList<>();
	
	public Comprador() {}
	
	public Comprador (String nome,String email,String senha,String tipoUsuario,String cpf,String endereco) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cpf = cpf;
		this.endereco = endereco;
		
	}

	public Comprador (String nome,String email,String senha,String tipoUsuario,String cpf,String endereco,double pontuacao) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
		this.tipoUsuario = tipoUsuario;
		this.cpf = cpf;
		this.endereco = endereco;
		this.pontuacao = pontuacao;
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

	/* *
	public void listarHistorico(){
		System.out.println("Histórico de Compras : ");
		for(Produto produto : listaHistorico){
			System.out.println("Id : " + produto.getId());
			System.out.println("Valor : " + produto.getValor());
			System.out.println("Tipo : " + produto.getTipo());
			System.out.println("Marca : " + produto.getMarca());
			//Talvez um do dia da compra
			System.out.println("-----------------------");
		}

	}*/
}

	 

