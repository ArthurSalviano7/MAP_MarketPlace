package test.example;
import Fachada.Fachada;
import entidades.Comprador;
import entidades.Loja;
import marketplace.MarketPlace;
import serializacao.Serializacao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FachadaTeste {
    private Fachada fachada;
    
    @BeforeEach
    public void setUpFachada() {
        fachada = new Fachada();
    }
    
    @Test
    public void testAutenticarCompradorAutenticado() {
        Comprador comprador = new Comprador();
        comprador.setNome("Comprador Teste");
        comprador.setEmail("jose@gmail.com");
        comprador.setSenha("1234");
        comprador.setTipoUsuario("Comprador");
        comprador.setCpf("12345678911");
        comprador.setEndereco("Endereço");
        fachada.listaDeObjetos.add(comprador);
        Serializacao.gravarArquivo(fachada.listaDeObjetos, "Arquivo teste autenticar");
        
        String mensagem = fachada.autenticar("jose@gmail.com", "1234");
        Assertions.assertEquals("Comprador autenticado", mensagem);
    }

    @Test
    public void testAutenticarLojaAutenticada() {
        Loja loja = new Loja(1, "PetStore", "petstore@outlook.com", "54321", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        fachada.listaDeObjetos.add(loja);


        String mensagem = fachada.autenticar("petstore@outlook.com", "54321");
        Assertions.assertEquals("Loja autenticada", mensagem);

    }

    @Test
    public void testAutenticarUsuarioNaoCadastrado() {
    	String mensagem = fachada.autenticar("emailNãoExistente@exemplo.com", "senha");
        Assertions.assertEquals("não autenticado", mensagem);
    }

    @Test
    public void testRecuperarIDListaVazia() {
    	fachada.listaDeObjetos.clear();
        int id = fachada.recuperarID();
        Assertions.assertEquals(1, id);
    }

    @Test
    public void testRecuperarIDComUltimaLoja() {
        Loja loja = new Loja(3, "Nome", "email@exemplo.com", "senha", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        fachada.listaDeObjetos.add(loja);
        
        int id = fachada.recuperarID();
        Assertions.assertEquals(4, id); //Id do ultimo objeto + 1
    }

    @Test
    public void testRecuperarIDComUltimoComprador() {
    	Comprador comprador = new Comprador(1, "Nome", "email@exemplo.com", "senha", "Comprador", "CPF", "Endereço");
        fachada.listaDeObjetos.add(comprador);
        
        int id = fachada.recuperarID();
        Assertions.assertEquals(2, id);
    }
    
    @Test
    public void testListarCompradores() {
    	fachada.listaCompradores.clear();
    	Comprador comprador = new Comprador(1, "Nome", "email@exemplo.com", "senha", "Comprador", "CPF", "Endereço");
    	Comprador comprador2 = new Comprador(2, "Comprador Teste", "jose@gmail.com", "senha", "Comprador", "CPF", "Endereço");

    	fachada.listaCompradores.add(comprador);
    	fachada.listaCompradores.add(comprador2);
    	String saidaEsperada = "Nome : Nome\r\nEmail : email@exemplo.com\r\n-------------------------\r\n"
                + "Nome : Comprador Teste\r\nEmail : jose@gmail.com\r\n-------------------------\r\n";
    	ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));
        
    	fachada.listarCompradores();
        System.setOut(System.out);

    	
        Assertions.assertEquals(saidaEsperada, saidaAtual.toString());
    }
    
    @Test
    public void testLoginInvalido() {
        String email = "emailinvalido@gmail.com";
        String senha = "senhainvalida";

        // Configura a entrada do usuário simulando um login inválido
        String input = email + System.lineSeparator() + senha + System.lineSeparator();
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        
        //Redireciona a saída padrão para um ByteArrayOutputStream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        System.setOut(printStream);

        MarketPlace.Login();
        // Captura o conteúdo da saída do sistema
        String output = outputStream.toString();
        Assertions.assertTrue(output.contains("Autenticação falhou"));
        
        System.setIn(System.in);
        System.setOut(System.out);
    }

   }

    

