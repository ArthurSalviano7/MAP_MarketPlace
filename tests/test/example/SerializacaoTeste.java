package test.example;
import serializacao.Serializacao;


import java.io.File;
import java.io.IOException;

import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SerializacaoTeste {

    @Test
    public void testeGravarArquivo() {
        ArrayList<Object> lista = new ArrayList<>();
        lista.add("Item 1");
        lista.add("Item 2");
        String nomeArquivo = "test.arq";

        Serializacao.gravarArquivo(lista, nomeArquivo);

        File arquivo = new File(nomeArquivo);
        Assertions.assertTrue(arquivo.exists());
    }
    
    @Test
    public void testeLerArquivo() {
        ArrayList<Object> listaEsperada = new ArrayList<>();
        listaEsperada.add("Item 1");
        listaEsperada.add("Item 2");
        String nomeArquivo = "teste.arq";

        Serializacao.gravarArquivo(listaEsperada, nomeArquivo);

        ArrayList<Object> listaAtual = Serializacao.lerArquivo(nomeArquivo);

        Assertions.assertEquals(listaEsperada, listaAtual);
    }
    
    @Test
    public void testeGravarArquivo_IOException() throws IOException {
        ArrayList<Object> lista = new ArrayList<>();
        String nomeArquivo = "readonly.arq";

        // Altere a permissão do arquivo para somente leitura
        File arquivo = new File(nomeArquivo);
        arquivo.setReadOnly();

        Serializacao.gravarArquivo(lista, nomeArquivo);
    }
    
    
    
}

    

