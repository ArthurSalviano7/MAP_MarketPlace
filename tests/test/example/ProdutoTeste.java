package test.example;
import Fachada.Fachada;
import entidades.Loja;
import entidades.Produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTeste {
    private ArrayList<Produto> listaDeProdutos;
    

    private Fachada fachada;
    
    
    @BeforeEach
    public void setupFachada() {
        fachada = new Fachada();
    }
    
    @BeforeEach
    public void setupProduto() {
        listaDeProdutos = new ArrayList<>();
    }
    
    @Test
    public void testCadastrarProduto() {
        Produto produto = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        produto.cadastrar();
        assertEquals(1, fachada.listaProdutos.size());
        assertEquals(1, produto.getId());
    }

    @Test
    public void testExibirProdutoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        String resultado = produto1.exibir(1, listaDeProdutos);
        assertEquals("Produto [id=1, descricao=Camiseta, quantidade=10, valor=29.99, tipo=Vestuário, marca=Nike]", resultado);
    }

    @Test
    public void testExibirProdutoNaoExistente() {
        Produto produto1 = new Produto("Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        String resultado = produto1.exibir(3, listaDeProdutos);
        assertEquals("Produto não encontrado\n", resultado);
    }

    @Test
    public void testBuscarProdutoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        String resultado = produto1.buscar("camiseta", listaDeProdutos);
        assertEquals("Produto [id=1, descricao=Camiseta, quantidade=10, valor=29.99, tipo=Vestuário, marca=Nike]", resultado);
    }

    @Test
    public void testBuscarProdutoNaoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(produto1);
        produto2.setDescricao("Camiseta Azul");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        String resultado = produto1.buscar("sapato", listaDeProdutos);
        assertEquals("Produto não encontrado na busca\n", resultado);
    }

    @Test
    public void testAtualizarProdutoExistente() {
    	Loja loja = new Loja();
    	loja.cadastrar();
    	loja.setId(1);
    	
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        produto1.setIdLoja(1);
        produto1.cadastrar();

        Produto novoProduto = new Produto(1, "Camiseta Azul", 10, 29.99, "Vestuário", "Nike");
        String resultado = produto1.atualizar(novoProduto);
        assertEquals("Produto atualizado\n", resultado);
        assertEquals("Camiseta Azul", Fachada.listaProdutos.get(0).getDescricao());
    }

    @Test
    public void testAtualizarProdutoNaoExistente() {
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");

        Produto novoProduto = new Produto(3, "Jaqueta", 8, 79.99, "Vestuário", "Puma");
        String resultado = produto2.atualizar(novoProduto);
        assertEquals("Produto não encontrado\n", resultado);
    }

    @Test
    public void testRemoverProdutoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        fachada.listaProdutos.add(produto1);
        fachada.listaProdutos.add(produto2);

        String resultado = produto1.remover();
        assertEquals("Produto removido\n", resultado);
        assertEquals(1, fachada.listaProdutos.size());
    }

    @Test
    public void testRemoverProdutoNaoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        Produto produto3 = new Produto(3, "Jaqueta", 8, 79.99, "Vestuário", "Puma");
        String resultado = produto3.remover();
        assertEquals("Produto não encontrado \n", resultado);
        assertEquals(2, listaDeProdutos.size());
    }

    @Test
    public void testListar() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        produto1.listar(listaDeProdutos);
        // Verificar a saída no console
    }

    @Test
    public void testToString() {
        Produto produto = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        String resultado = produto.toString();
        assertEquals("Produto [id=1, descricao=Camiseta, quantidade=10, valor=29.99, tipo=Vestuário, marca=Nike]", resultado);
    }
}

    

