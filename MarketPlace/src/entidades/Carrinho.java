package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carrinho implements Serializable{
    private List<Produto> listaProdutos;
    private Double frete = 15.00;

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
    
    public void removerProduto(int id) {
        Produto produtoRemover = null;
        for (Produto produto : listaProdutos) {
            if (produto.getId() == id) {
                produtoRemover = produto;
                break;
            }
        }
        if (produtoRemover != null) {
            listaProdutos.remove(produtoRemover);
        }
    }

    public List<Produto> getListaProdutos(){
        return listaProdutos;
    }

    public void limparCarrinho(){
        listaProdutos.clear();
    }

    public double calcularTotalSemBeneficio(){
        double total = 0;
        for (Produto produto : listaProdutos){
            total += produto.getQuantidade() * produto.getValor();
        }
        total += frete;
        return total;
    }
    
    public double calcularTotalComBeneficio(){
        double total = 0;
        for (Produto produto : listaProdutos){
            total += produto.getQuantidade() * produto.getValor();
        }
        return total;
    }
}
