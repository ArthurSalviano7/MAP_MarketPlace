package test.example;
import Fachada.Fachada;
import entidades.Loja;
import entidades.Produto;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LojaTeste {
	
	private Loja loja;
    private ArrayList<Loja> listaDeLojas;

    private Fachada fachada;
    
    
    @BeforeEach
    public void setUpFachada() {
        fachada = new Fachada();
    }
    
    @BeforeEach
    public void setUpLoja() {
        loja = new Loja();
        loja.setId(1);
        loja.setNome("Loja Teste");
        loja.setEmail("teste@teste.com");
        loja.setSenha("senha123");
        loja.setTipoUsuario("Loja");
        loja.setCnpj("123456789");
        loja.setCpf("123456789");
        loja.setEndereco("Endereço");
        loja.setReputacao(4.5);
        loja.setConceito("Bom");
        listaDeLojas = new ArrayList<>();
    }
    
    @Test
    public void testCadastrarLoja() {
    	fachada.listaLojas.clear();
        loja.cadastrar();
        Assertions.assertEquals(1, fachada.listaLojas.size());
        Assertions.assertEquals(loja, fachada.listaLojas.get(0));
    }

    @Test
    public void testExibirLoja() {
    	fachada.listaLojas.clear();
    	loja.cadastrar();
    	String resultado = loja.exibir(1, fachada.listaLojas);
        Assertions.assertEquals(loja.toString(), resultado);
    }

    @Test
    public void testBuscarLoja() {
        listaDeLojas.add(loja);
        String resultado = loja.buscar("Teste", listaDeLojas);
        Assertions.assertEquals(loja.toString(), resultado);
    }

    @Test
    public void testAtualizarLoja() {
    	fachada.listaLojas.clear();
        loja.cadastrar();
        Loja novaLoja = new Loja(1, "Nova Loja", "nova@teste.com", "novasenha", "Novo Tipo", "987654321", "987654321", "Novo Endereço", 3.8, "Ruim");
        String mensagem = loja.atualizar(novaLoja);
        Assertions.assertEquals("Loja atualizada com sucesso \n", mensagem);
        Assertions.assertEquals(novaLoja, fachada.listaLojas.get(0));
    }
    
    @Test
    public void testRemoverLoja() {
    	fachada.listaLojas.clear();
        // Caso 1: Lista vazia
        String mensagemVazia = loja.remover();
        Assertions.assertEquals("Loja n�o encontrada \n", mensagemVazia);

        // Caso 2: Loja n�o encontrada
        Loja outraLoja = new Loja(2, "Outra Loja", "outra@teste.com", "outrasenha", "Outro Tipo", "987654321", "987654321", "Outro Endere�o", 4.2, "Bom");
        outraLoja.cadastrar(); // Adicione uma loja diferente da que voc� deseja remover
        String mensagemNaoEncontrada = loja.remover();
        Assertions.assertEquals("Loja n�o encontrada \n", mensagemNaoEncontrada);

        // Caso 3: Loja encontrada e removida
        loja.cadastrar();
        String mensagemRemovida = fachada.listaLojas.get(0).remover();
        Assertions.assertEquals("Loja removida \n", mensagemRemovida);
        Assertions.assertEquals(0, listaDeLojas.size());
    }

    @Test
    public void testListarLoja() {
        listaDeLojas.add(loja);
        Loja outraLoja = new Loja(2, "Loja 2", "loja2@teste.com", "senha456", "Tipo", "987654321", "987654321", "Endereço 2");
        listaDeLojas.add(outraLoja);
        loja.listar(listaDeLojas);
    }

    @Test
    public void testListarProdutosLoja() {
        Produto produto1 = new Produto(1, "Produto 1", 10, 39.99, "Vestuário", "Adidas");
        Produto produto2 = new Produto(2, "Produto 2", 15, 20.25, "Livros", "Marca");
        loja.getListaDeProdutos().add(produto1);
        loja.getListaDeProdutos().add(produto2);
        loja.listarProdutos();
    }
    
   }

    

