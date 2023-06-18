package test.example;
import Fachada.Fachada;
import entidades.Comprador;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CompradorTeste {
	
    private ArrayList<Comprador> listaDeCompradores;
    private Fachada fachada;
    
    @BeforeEach
    public void setUpFachada() {
        fachada = new Fachada();
    }
    
    @BeforeEach
    public void setUpComprador() {
        listaDeCompradores = new ArrayList<>();
    }
    
    @Test
    public void testCadastrarComprador(){
    	Comprador comprador = new Comprador();
        comprador.setNome("Comprador Teste");
        comprador.setEmail("jose@gmail.com");
        comprador.setSenha("1234");
        comprador.setTipoUsuario("Comprador");
        comprador.setCpf("12345678911");
        comprador.setEndereco("Endereço");
        
        comprador.cadastrar();

        Assertions.assertEquals(1, Fachada.listaCompradores.size());
        Assertions.assertEquals(comprador, Fachada.listaCompradores.get(0));

    }
    @Test
    public void testExibirComprador() {
        Comprador comprador = new Comprador(1, "Joao", "joao@email.com", "senha", "comprador", "123456789", "123 endereco");
        listaDeCompradores.add(comprador);

        String expectedOutput = "ID: 1\nNome: Joao\nEmail: joao@email.com\nSenha: senha\nTipo de Usuário: comprador\nCPF: 123456789\nEndereço: 123 endereco\n";
        String actualOutput = comprador.exibir(1, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testExibirCompradorNaoExistente() {
        Comprador comprador = new Comprador(1, "Joao", "joao@email.com", "senha", "comprador", "123456789", "123 endereco");
        listaDeCompradores.add(comprador);

        String expectedOutput = "Usuário Comprador não encontrado \n";
        String actualOutput = comprador.exibir(5, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testBuscarComprador() {
        Comprador comprador1 = new Comprador(1, "Joao", "joao@email.com", "senha", "comprador", "123456789", "123 endereco");
        Comprador comprador2 = new Comprador(2, "Jennifer", "jennifer@email.com", "novaSenha", "comprador", "123456789", "321 Endereco");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String expectedOutput = "ID: 2\nNome: Jennifer\nEmail: jennifer@email.com\nSenha: novaSenha\nTipo de Usuário: comprador\nCPF: 123456789\nEndereço: 321 Endereco\n";
        String actualOutput = comprador1.buscar("Jennifer", listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testBuscarCompradorNaoExistente() {
        Comprador comprador1 = new Comprador(1, "Joao", "joao@email.com", "senha", "comprador", "123456789", "123 endereco");
        listaDeCompradores.add(comprador1);

        String expectedOutput = "Usuário Comprador não foi encontrado na busca \n";
        String actualOutput = comprador1.buscar("jennifer", listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAtualizarComprador() {
    	fachada.listaCompradores.clear();
        Comprador comprador1 = new Comprador(1, "Jonas", "jonas@email.com", "senha", "comprador", "123456789", "123 Endereco");
        Comprador comprador2 = new Comprador(2, "Jennifer", "jennifer@email.com", "novaSenha", "comprador", "123456789", "456 Endereco");

        comprador1.cadastrar();

        String expectedOutput = "Usuário Comprador atualizado com sucesso \n";
        String actualOutput = comprador1.atualizar(comprador2);

        Assertions.assertEquals(expectedOutput, actualOutput);
        Assertions.assertEquals(comprador2, fachada.listaCompradores.get(0));
    }
    
    @Test
    public void testAtualizarCompradorNaoExistente() {
        Comprador comprador1 = new Comprador(1, "Joao", "joao@email.com", "senha", "comprador", "123456789", "123 endereco");
        Comprador comprador2 = new Comprador(1, "Jonas", "jonas@email.com", "senha", "comprador", "123456789", "123 Endereco");
        listaDeCompradores.add(comprador1);

        String expectedOutput = "Usuário Comprador não encontrado\n";
        String actualOutput = comprador2.atualizar(comprador1);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }
    
    @Test
    public void testRemoverComprador() {
        Comprador comprador = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        comprador.cadastrar();

        String saidaEsperada = "Usuário Comprador removido \n";
        String saidaAtual = comprador.remover();

        Assertions.assertEquals(saidaEsperada, saidaAtual);
    }
    
    @Test
    public void testRemoverCompradorNaoExistente() {
        Comprador comprador = new Comprador(5, "Nome", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");

        String saidaEsperada = "Usuário Comprador não encontrado \n";
        String saidaAtual = comprador.remover();

        Assertions.assertEquals(saidaEsperada, saidaAtual);
    }

    @Test
    public void testListarComprador() {
    	fachada.listaCompradores.clear();
        Comprador comprador1 = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        Comprador comprador2 = new Comprador(2, "Ciclano", "ciclano@example.com", "senha", "comprador", "987654321", "Rua B");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String saidaEsperada = "Lista de Compradores:\r\nID -> Nome do Usuário -> Email\r\n1 -> Fulano -> fulano@example.com\r\n2 -> Ciclano -> ciclano@example.com\r\n";
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        comprador1.listar(listaDeCompradores);
        
        Assertions.assertEquals(saidaEsperada, saidaAtual.toString());
    }
}

    

