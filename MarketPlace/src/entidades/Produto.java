package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import Fachada.Fachada;


public class Produto implements IFCrud<Produto>, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 10L;

	private static int idProduto = 0;
	
	private int id;
	private String descricao;
	private int quantidade;
	private double valor;
	private String tipo; //categoria do produto
	private String marca;
	private int idLoja;
	
	public Produto() {}
	
	public Produto(String descricao, int quantidade, double valor, String tipo, String marca) {
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.tipo = tipo;
		this.marca = marca;
	}
	
	public Produto(int id, String descricao, int quantidade, double valor, String tipo, String marca) {
		this.id = id;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.valor = valor;
		this.tipo = tipo;
		this.marca = marca;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getIdLoja(){
		return idLoja;
	}
	
	public void setIdLoja(int idLoja){
		this.idLoja = idLoja;
	}

	private static int proximoId(){
		idProduto++;
		return idProduto;
	}
	//CRUD Produto:
	
	//Cadastra o próprio objeto produto na loja especificada
	public void cadastrar() {
    	this.setId(proximoId());
    	Loja loja = Fachada.getLojaPorId(idLoja); // Obter a loja correspondente ao ID
    	if (loja != null) {
        	loja.getListaDeProdutos().add(this); // Adicionar o produto à lista de produtos da loja
        	System.out.println("Produto cadastrado com sucesso");
    } else {
        System.out.println("Loja não encontrada");
    }
}


	/* 
	public void cadastrar() {
    this.setId(idProduto);
    idProduto++;
    Loja loja = Fachada.getLojaPorId(idLoja); // Obter a loja correspondente ao ID
    if (loja != null) {
        loja.getListaDeProdutos().add(this); // Adicionar o produto à lista de produtos da loja
        System.out.println("Produto cadastrado com sucesso");
    } else {
        System.out.println("Loja não encontrada");
    }
}
	*/

	
	//Exibe o produto em determinada loja pelo id
	public String exibir(int id, ArrayList<Produto> listaDeProdutos) { 
		
		for(int i = 0; i < listaDeProdutos.size(); i++){
			if (listaDeProdutos.get(i).getId() == id) {
				return listaDeProdutos.get(i).toString();
			}
		}
		
		return "Produto não encontrado\n";
	}
	
	public String buscar(String nomeBuscado, ArrayList<Produto> listaDeProdutos) {
		String nomeDoProduto;
		for(int i = 0; i < listaDeProdutos.size(); i++){
			nomeDoProduto = listaDeProdutos.get(i).getDescricao().toLowerCase();

			if (nomeDoProduto.contains(nomeBuscado.toLowerCase())){
				Produto produto = listaDeProdutos.get(i);
				return produto.toString();
			}
		}
		return "Produto não encontrado na busca\n";
	}

	//Atualiza o produto através do id original, o novo produto já atualizado e a lista de produtos
	public String atualizar(Produto novoProduto) {

		for(int i = 0; i < Fachada.listaProdutos.size(); i++){
			int idDoProduto = Fachada.listaProdutos.get(i).getId();

			if (idDoProduto == this.id){
				Fachada.listaProdutos.set(i, novoProduto);
				return "Produto atualizado\n";
			}
		}
		return "Produto não encontrado\n";
	}

	public String remover() {
		for(int i = 0; i < Fachada.listaProdutos.size(); i++){
			int idDoProduto = Fachada.listaProdutos.get(i).getId();

			if (idDoProduto == this.id){
				Fachada.listaProdutos.remove(i);
				return "Produto removido\n";
			}
		}
		return "Produto não encontrado \n";
	}

	public void listar(ArrayList<Produto> listaDeProdutos) {
		System.out.println("ID -> Descricao -> Valor");

		for(Produto produto : listaDeProdutos) {
			System.out.println(produto.getId() + " -> " + produto.getDescricao() + " -> R$" + produto.getValor());
		}
		System.out.println();
	}
	
	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", valor=" + valor
				+ ", tipo=" + tipo + ", marca=" + marca + "]";
	}
	
	
	
	
	
	
}
