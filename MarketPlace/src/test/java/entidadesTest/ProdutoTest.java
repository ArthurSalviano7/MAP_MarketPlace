package entidadesTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import entidades.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProdutoTest {

    private ArrayList<Produto> listaDeProdutos;

    @BeforeEach
    public void setUp() {
        listaDeProdutos = new ArrayList<>();
    }

    @Test
    public void testCadastrar() {
        Produto produto = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        produto.cadastrar(listaDeProdutos);
        assertEquals(1, listaDeProdutos.size());
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
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
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
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);
        String resultado = produto1.buscar("sapato", listaDeProdutos);
        assertEquals("Produto não encontrado na busca\n", resultado);
    }

    @Test
    public void testAtualizarProdutoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        Produto novoProduto = new Produto(1, "Camiseta Azul", 10, 29.99, "Vestuário", "Nike");
        String resultado = produto1.atualizar(novoProduto, listaDeProdutos);
        assertEquals("Produto atualizado\n", resultado);
        assertEquals("Camiseta Azul", listaDeProdutos.get(0).getDescricao());
    }

    @Test
    public void testAtualizarProdutoNaoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        Produto novoProduto = new Produto(3, "Jaqueta", 8, 79.99, "Vestuário", "Puma");
        String resultado = produto1.atualizar(novoProduto, listaDeProdutos);
        assertEquals("Produto não encontrado\n", resultado);
    }

    @Test
    public void testRemoverProdutoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        String resultado = produto1.remover(listaDeProdutos);
        assertEquals("Produto removido\n", resultado);
        assertEquals(1, listaDeProdutos.size());
    }

    @Test
    public void testRemoverProdutoNaoExistente() {
        Produto produto1 = new Produto(1, "Camiseta", 10, 29.99, "Vestuário", "Nike");
        Produto produto2 = new Produto(2, "Calça", 5, 59.99, "Vestuário", "Adidas");
        listaDeProdutos.add(produto1);
        listaDeProdutos.add(produto2);

        Produto produto3 = new Produto(3, "Jaqueta", 8, 79.99, "Vestuário", "Puma");
        String resultado = produto3.remover(listaDeProdutos);
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
