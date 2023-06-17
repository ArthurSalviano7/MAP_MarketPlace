package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable{
    private List<Produto> listaProdutos;

    public Carrinho(){
        listaProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto, int quantidade){
    	Produto copiaProduto = new Produto(produto);
        copiaProduto.setQuantidade(quantidade);
    	listaProdutos.add(copiaProduto);
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
            total += produto.getQuantidade() * produto.getValor();
        }
        return total;
    }
}
