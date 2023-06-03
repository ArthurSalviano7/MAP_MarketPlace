package test.example;
import Fachada.Fachada;
import entidades.Comprador;
import entidades.Loja;
import entidades.Produto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Testes {
	
	private Loja loja;
    private ArrayList<Loja> listaDeLojas;
    private ArrayList<Produto> listaDeProdutos;
    private ArrayList<Comprador> listaDeCompradores;
    private ArrayList<Object> listaFachada;

    private Fachada fachada;

    @BeforeEach
    public void setUpFachada() {
        fachada = new Fachada();
        listaFachada = new ArrayList<>();
    }
    
    @BeforeEach
    public void setUpProduto() {
        listaDeProdutos = new ArrayList<>();
    }
    
    @BeforeEach
    public void setUpLoja() {
        loja = new Loja();
        loja.setNome("Loja Teste");
        loja.setEmail("teste@teste.com");
        loja.setSenha("senha123");
        loja.setTipoUsuario("Tipo");
        loja.setCnpj("123456789");
        loja.setCpf("123456789");
        loja.setEndereco("Endereço");
        loja.setReputacao(4.5);
        loja.setConceito("Bom");
        listaDeLojas = new ArrayList<>();
    }
    
    @BeforeEach
    public void setUpComprador() {
        listaDeCompradores = new ArrayList<>();
    }
    
    
    //TESTES PARA FACHADA:
    @Test
    public void testAutenticarCompradorAutenticado() {
        // Arrange
        Comprador comprador = new Comprador(1, "Jose", "jose@gmail.com", "1234", "Comprador", "12345678922", "rua Clementino Procropio");
        fachada.cadastrarComprador(comprador.getNome(), comprador.getEmail(), comprador.getSenha(), comprador.getCpf(), comprador.getEndereco());

        // Act
        fachada.autenticar("jose@gmail.com", "1234");

    }

    @Test
    public void testAutenticarLojaAutenticada() {
        // Arrange
        Loja loja = new Loja(1, "PetStore", "petstore@outlook.com", "54321", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        fachada.cadastrarLoja(loja.getNome(), loja.getEmail(), loja.getSenha(), loja.getCnpj(), loja.getCpf(), loja.getEndereco());


        // Act
        fachada.autenticar("petstore@outlook.com", "54321");
    }

    @Test
    public void testAutenticarUsuarioNaoCadastrado() {
        
    	String mensagem = fachada.autenticar("email@exemplo.com", "senha");
        Assertions.assertEquals("Usuário não cadastrado!!", mensagem);
    }

    @Test
    public void testRecuperarIDComObjetosVazios() {
    	
        int id = fachada.recuperarID();
        Assertions.assertEquals(1, id);

    }

    @Test
    public void testRecuperarIDComUltimaLoja() {
        Loja loja = new Loja(1, "Nome", "email@exemplo.com", "senha", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        fachada.cadastrarLoja("Nome", "email@exemplo.com", "senha", "CNPJ", "CPF", "Endereço");
        
        int id = fachada.recuperarID();
        Assertions.assertEquals(2, id);

    }

    @Test
    public void testRecuperarIDComUltimoComprador() {

    	Comprador comprador = new Comprador(1, "Nome", "email@exemplo.com", "senha", "Comprador", "CPF", "Endereço");
        fachada.cadastrarComprador("Nome", "email@exemplo.com", "senha", "CPF", "Endereço");
        
        int id = fachada.recuperarID();
        Assertions.assertEquals(2, id);
    }

    //FIM DOS TESTES PARA FACHADA
    
    
    //TESTES PARA LOJA:
    @Test
    public void testCadastrarLoja() {
        loja.cadastrar(listaDeLojas);
        Assertions.assertEquals(1, listaDeLojas.size());
        Assertions.assertEquals(loja, listaDeLojas.get(0));
    }

    @Test
    public void testExibirLoja() {
        listaDeLojas.add(loja);
        String resultado = loja.exibir(1, listaDeLojas);
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
        listaDeLojas.add(loja);
        Loja novaLoja = new Loja(1, "Nova Loja", "nova@teste.com", "novasenha", "Novo Tipo", "987654321", "987654321", "Novo Endereço", 3.8, "Ruim");
        String mensagem = loja.atualizar(novaLoja, listaDeLojas);
        Assertions.assertEquals("Loja atualizada com sucesso \n", mensagem);
        Assertions.assertEquals(novaLoja, listaDeLojas.get(0));
    }

    @Test
    public void testRemoverLoja() {
        listaDeLojas.add(loja);
        String mensagem = loja.remover(listaDeLojas);
        Assertions.assertEquals("Loja removida \n", mensagem);
        Assertions.assertEquals(0, listaDeLojas.size());
    }

    @Test
    public void testListarLoja() {
        listaDeLojas.add(loja);
        Loja outraLoja = new Loja(2, "Loja 2", "loja2@teste.com", "senha456", "Tipo", "987654321", "987654321", "Endereço 2", 4.2, "Bom");
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
    
    //FIM DOS TESTES PARA LOJA
    
    //TESTES PARA COMPRADOR:
    @Test
    public void testCadastrarComprador(){
        Comprador comprador = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        comprador.cadastrar(listaDeCompradores);

        Assertions.assertEquals(1, listaDeCompradores.size());
        Assertions.assertEquals(comprador, listaDeCompradores.get(0));

    }
    @Test
    public void testExibirComprador() {
        Comprador comprador = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        listaDeCompradores.add(comprador);

        String expectedOutput = "ID: 1\nNome: John Doe\nEmail: john@example.com\nSenha: password\nTipo de Usuário: buyer\nCPF: 123456789\nEndereço: 123 Street\n";
        String actualOutput = comprador.exibir(1, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testBuscarComprador() {
        Comprador comprador1 = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        Comprador comprador2 = new Comprador(2, "Jane Smith", "jane@example.com", "password", "buyer", "987654321", "456 Street");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String expectedOutput = "ID: 2\nNome: Jane Smith\nEmail: jane@example.com\nSenha: password\nTipo de Usuário: buyer\nCPF: 987654321\nEndereço: 456 Street\n";
        String actualOutput = comprador1.buscar("jane", listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAtualizarComprador() {
        Comprador comprador1 = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        Comprador comprador2 = new Comprador(1, "John Smith", "john@example.com", "newpassword", "buyer", "123456789", "456 Street");
        listaDeCompradores.add(comprador1);

        String expectedOutput = "Usuário Comprador atualizado com sucesso \n";
        String actualOutput = comprador1.atualizar(comprador2, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
        Assertions.assertEquals(comprador2, listaDeCompradores.get(0));
    }
    @Test
    public void testarRemoverComprador() {
        Comprador comprador = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        listaDeCompradores.add(comprador);

        String saidaEsperada = "Usuário Comprador removido \n";
        String saidaAtual = comprador.remover(listaDeCompradores);

        Assertions.assertEquals(saidaEsperada, saidaAtual);
        Assertions.assertEquals(0, listaDeCompradores.size());
    }

    @Test
    public void testarListarComprador() {
        Comprador comprador1 = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        Comprador comprador2 = new Comprador(2, "Ciclano", "ciclano@example.com", "senha", "comprador", "987654321", "Rua B");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String saidaEsperada = "Lista de Compradores:\nID -> Nome do Usuário -> Email\n1 -> Fulano -> fulano@example.com\n2 -> Ciclano -> ciclano@example.com\n";
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        comprador1.listar(listaDeCompradores);

        Assertions.assertEquals(saidaEsperada, saidaAtual.toString());
    }
    
    //FIM DOS TESTES PARA COMPRADOR
    
    //TESTES PARA PRODUTO:
    @Test
    public void testCadastrarProduto() {
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
