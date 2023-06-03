package entidadesTest;

import entidades.Comprador;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
public class CompradorTest {
    private ArrayList<Comprador> listaDeCompradores;

    @BeforeEach
    public void setup() {
        listaDeCompradores = new ArrayList<>();
    }

    @Test
    public void testCadastrar(){
        Comprador comprador = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        comprador.cadastrar(listaDeCompradores);

        Assertions.assertEquals(1, listaDeCompradores.size());
        Assertions.assertEquals(comprador, listaDeCompradores.get(0));

    }
    @Test
    public void testExibir() {
        Comprador comprador = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        listaDeCompradores.add(comprador);

        String expectedOutput = "ID: 1\nNome: John Doe\nEmail: john@example.com\nSenha: password\nTipo de Usuário: buyer\nCPF: 123456789\nEndereço: 123 Street\n";
        String actualOutput = comprador.exibir(1, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testBuscar() {
        Comprador comprador1 = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        Comprador comprador2 = new Comprador(2, "Jane Smith", "jane@example.com", "password", "buyer", "987654321", "456 Street");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String expectedOutput = "ID: 2\nNome: Jane Smith\nEmail: jane@example.com\nSenha: password\nTipo de Usuário: buyer\nCPF: 987654321\nEndereço: 456 Street\n";
        String actualOutput = comprador1.buscar("jane", listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testAtualizar() {
        Comprador comprador1 = new Comprador(1, "John Doe", "john@example.com", "password", "buyer", "123456789", "123 Street");
        Comprador comprador2 = new Comprador(1, "John Smith", "john@example.com", "newpassword", "buyer", "123456789", "456 Street");
        listaDeCompradores.add(comprador1);

        String expectedOutput = "Usuário Comprador atualizado com sucesso \n";
        String actualOutput = comprador1.atualizar(comprador2, listaDeCompradores);

        Assertions.assertEquals(expectedOutput, actualOutput);
        Assertions.assertEquals(comprador2, listaDeCompradores.get(0));
    }
    @Test
    public void testarRemover() {
        Comprador comprador = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        listaDeCompradores.add(comprador);

        String saidaEsperada = "Usuário Comprador removido \n";
        String saidaAtual = comprador.remover(listaDeCompradores);

        Assertions.assertEquals(saidaEsperada, saidaAtual);
        Assertions.assertEquals(0, listaDeCompradores.size());
    }

    @Test
    public void testarListar() {
        Comprador comprador1 = new Comprador(1, "Fulano", "fulano@example.com", "senha", "comprador", "123456789", "Rua A");
        Comprador comprador2 = new Comprador(2, "Ciclano", "ciclano@example.com", "senha", "comprador", "987654321", "Rua B");
        listaDeCompradores.add(comprador1);
        listaDeCompradores.add(comprador2);

        String saidaEsperada = "Lista de Compradores: \n\nID -> Nome do Usuário -> Email\n1 -> Fulano -> fulano@example.com\n2 -> Ciclano -> ciclano@example.com\n";
        ByteArrayOutputStream saidaAtual = new ByteArrayOutputStream();
        System.setOut(new PrintStream(saidaAtual));

        comprador1.listar(listaDeCompradores);

        Assertions.assertEquals(saidaEsperada, saidaAtual.toString());
    }
}

