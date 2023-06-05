package entidades;

import java.util.ArrayList;

public interface IFCrud<tipo> {
	
	public void cadastrar();
	public String exibir(int id, ArrayList<tipo> lista);
	public String buscar(String nome, ArrayList<tipo> lista);
	public String atualizar(tipo novoObjeto);
	public String remover();
	public void listar(ArrayList<tipo> lista);
}
