package test.example;
import Fachada.Fachada;
import entidades.Comprador;
import entidades.Loja;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class FachadaTest {

    private Fachada fachada;

    @BeforeEach
    public void setUp() {
        fachada = new Fachada();
    }

    @Test
    public void testAutenticarCompradorAutenticado() {
        // Arrange
        Comprador comprador = new Comprador(1, "Jose", "jose@gmail.com", "1234", "Comprador", "12345678922", "rua Clementino Procropio");
        Fachada.listaCompradores.add(comprador);

        // Act
        fachada.autenticar("jose@gmail.com", "1234");

        // Assert
        // Incluir asserções relevantes
    }

    @Test
    public void testAutenticarLojaAutenticada() {
        // Arrange
        Loja loja = new Loja(1, "PetStore", "petstore@outlook.com", "54321", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        Fachada.listaLojas.add(loja);

        // Act
        fachada.autenticar("petstore@outlook.com", "54321");

        // Assert
        // Incluir asserções relevantes
    }

    @Test
    public void testAutenticarUsuarioNaoCadastrado() {
        // Arrange
        Fachada.listaCompradores.clear();
        Fachada.listaLojas.clear();

        // Act
        fachada.autenticar("email@exemplo.com", "senha");

        // Assert
        // Incluir asserções relevantes
    }

    @Test
    public void testRecuperarIDComObjetosVazios() {
        // Arrange
        Fachada.listaDeObjetos.clear();

        // Act
        Fachada.recuperarID();

        // Assert
        // Incluir asserções relevantes
    }

    @Test
    public void testRecuperarIDComUltimaLoja() {
        // Arrange
        Fachada.listaDeObjetos.clear();
        Loja loja = new Loja(1, "Nome", "email@exemplo.com", "senha", "Loja", "CNPJ", "CPF", "Endereço", 5.0, "Conceito");
        Fachada.listaDeObjetos.add(loja);

        // Act
        Fachada.recuperarID();

        // Assert
        // Incluir asserções relevantes
    }

    @Test
    public void testRecuperarIDComUltimoComprador() {
        // Arrange
        Fachada.listaDeObjetos.clear();
        Comprador comprador = new Comprador(1, "Nome", "email@exemplo.com", "senha", "Comprador", "CPF", "Endereço");
        Fachada.listaDeObjetos.add(comprador);

        // Act
        Fachada.recuperarID();

        // Assert
        // Incluir asserções relevantes
    }

    // Adicione mais casos de teste conforme necessário para cobrir todos os cenários e métodos da classe Fachada.
}
