package entidades;

import java.util.ArrayList;

public interface IFCrud<tipo> {

    public void cadastrar(ArrayList<tipo> lista);
    public String exibir(int id, ArrayList<tipo> lista);
    public String buscar(String nome, ArrayList<tipo> lista);
    public String atualizar(tipo novoObjeto, ArrayList<tipo> lista);
    public String remover(ArrayList<tipo> lista);
    public void listar(ArrayList<tipo> lista);
}
