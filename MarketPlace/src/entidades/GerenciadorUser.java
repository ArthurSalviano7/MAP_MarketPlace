package entidades;

import java.io.Serializable;
import java.util.ArrayList;

public class GerenciadorUser implements Serializable {

    private ArrayList<Comprador> listaCompradores;
    private ArrayList<Loja> listaLojas;

    public GerenciadorUser(){
        listaCompradores = new ArrayList<>();
        listaLojas = new ArrayList<>();
    }


    public void cadastrarComprador(String nome,String email,String tipoUsuario,String cpf,String endereco){
        Comprador comprador = new Comprador(nome, email, email, tipoUsuario, cpf, endereco);   
        listaCompradores.add(comprador);
        System.out.println("Comprador cadastrado com sucesso!!");
    }

    public void cadastrarLoja(String nome, String email, String senha, String tipoUsuario,String cnpj, String cpf, String endereco){
        Loja loja = new Loja(nome, email, senha, tipoUsuario, cnpj, cpf, endereco, 0, null);
        listaLojas.add(loja);
        System.out.println("Loja cadastrada com sucesso!!");
    }

    
    
}
