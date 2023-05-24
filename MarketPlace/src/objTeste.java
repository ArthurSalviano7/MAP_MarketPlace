import java.io.Serializable;

public class objTeste implements Serializable{
	private String nome;
	private double altura;
	
	public objTeste(String nome, double altura) {
		this.nome = nome;
		this.altura = altura;
	}
	
	@Override
	public String toString() {
		return "" + nome + " " + altura + "";
	}
}
