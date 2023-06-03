package test.example;

import entidades.Loja;
import entidades.Produto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class LojaTest {

    private Loja loja;
    private ArrayList<Loja> listaDeLojas;

    @BeforeEach
    public void setUp() {
        loja = new Loja(1, "Loja Teste", "teste@teste.com", "senha123", "Tipo", "123456789", "123456789", "Endereço", 4.5, "Bom");
        listaDeLojas = new ArrayList<>();
    }

    @Test
    public void testCadastrar() {
        loja.cadastrar(listaDeLojas);
        Assertions.assertEquals(1, listaDeLojas.size());
        Assertions.assertEquals(loja, listaDeLojas.get(0));
    }

    @Test
    public void testExibir() {
        listaDeLojas.add(loja);
        String resultado = loja.exibir(1, listaDeLojas);
        Assertions.assertEquals(loja.toString(), resultado);
    }

    @Test
    public void testBuscar() {
        listaDeLojas.add(loja);
        String resultado = loja.buscar("Teste", listaDeLojas);
        Assertions.assertEquals(loja.toString(), resultado);
    }

    @Test
    public void testAtualizar() {
        listaDeLojas.add(loja);
        Loja novaLoja = new Loja(1, "Nova Loja", "nova@teste.com", "novasenha", "Novo Tipo", "987654321", "987654321", "Novo Endereço", 3.8, "Ruim");
        String mensagem = loja.atualizar(novaLoja, listaDeLojas);
        Assertions.assertEquals("Loja atualizada com sucesso \n", mensagem);
        Assertions.assertEquals(novaLoja, listaDeLojas.get(0));
    }

    @Test
    public void testRemover() {
        listaDeLojas.add(loja);
        String mensagem = loja.remover(listaDeLojas);
        Assertions.assertEquals("Loja removida \n", mensagem);
        Assertions.assertEquals(0, listaDeLojas.size());
    }

    @Test
    public void testListar() {
        listaDeLojas.add(loja);
        Loja outraLoja = new Loja(2, "Loja 2", "loja2@teste.com", "senha456", "Tipo", "987654321", "987654321", "Endereço 2", 4.2, "Bom");
        listaDeLojas.add(outraLoja);
        loja.listar(listaDeLojas);
    }

    @Test
    public void testListarProdutos() {
        Produto produto1 = new Produto(1, "Produto 1", 10, 39.99, "Vestuário", "Adidas");
        Produto produto2 = new Produto(2, "Produto 2", 15, 20.25, "Livros", "Marca");
        loja.getListaDeProdutos().add(produto1);
        loja.getListaDeProdutos().add(produto2);
        loja.listarProdutos();
    }
}
