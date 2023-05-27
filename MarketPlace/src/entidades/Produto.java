package entidades;

import java.io.Serializable;


public class Produto implements Serializable{
	
	private int id;
	private String descricao;
	private int quantidade;
	private double valor;
	private String tipo; //categoria do produto
	private String marca;
	
	public Produto() {}
	
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", descricao=" + descricao + ", quantidade=" + quantidade + ", valor=" + valor
				+ ", tipo=" + tipo + ", marca=" + marca + "]";
	}
	
	
	
	
}
