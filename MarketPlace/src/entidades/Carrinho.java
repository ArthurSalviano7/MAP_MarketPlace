package entidades;

import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private List<Produto> listaProdutos;

    public Carrinho(){
        listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto){
        listaProdutos.add(produto);
    }

    public void removerProduto(Produto produto){
        listaProdutos.remove(produto);
    }

    public List<Produto> getListaProdutos(){
        return listaProdutos;
    }

    public void limparCarrinho(){
        listaProdutos.clear();
    }

    public double calcularTotal(){
        double total = 0;
        for (Produto produto : listaProdutos){
            total += produto.getValor();
        }
        return total;
    }
}
