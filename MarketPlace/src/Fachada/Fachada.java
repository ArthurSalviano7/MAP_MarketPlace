package Fachada;

import entidades.Comprador;
import entidades.Loja;
import java.util.ArrayList;
import java.util.List;


public class Fachada {
    private List<Comprador> listaCompradores;
    private List<Loja> listaLojas;

    public Fachada(){
        listaCompradores = new ArrayList<>();
        listaLojas = new ArrayList<>();
    }

    public void autenticar(String email, String senha){
        boolean compradorAutenticado = false;
        boolean lojaAutenticada = false;

        for (Comprador comprador : listaCompradores){ 
            if(comprador.getEmail().equals(email) && comprador.getSenha().equals(senha)){
                compradorAutenticado = true;
                break; // Não percorrer a lista toda
            }
        }

        for(Loja loja : listaLojas){
            if(loja.getEmail().equals(email) && loja.getSenha().equals(senha)){
                lojaAutenticada = true;
                break; // Não percorrer a lista toda
            }
        }

        if(compradorAutenticado){ 
            exibirMenuComprador(); // Incompleto
        }
        else if(lojaAutenticada){
            exibirMenuLoja(); // Incompleto
        }
        else{
            System.out.println("Usuário não cadastro!!");
        }
        
    }

    public void listarCompradores(){
        for(Comprador comprador : listaCompradores){
            System.out.println(" Nome : " + comprador.getNome());
            System.out.println("Email : " + comprador.getEmail());
            System.out.println("-------------------------");
        }
    }

    public void listarLojas(){
        for(Loja loja : listaLojas){
            System.out.println(" Nome : " + loja.getNome());
            System.out.println("Email : " + loja.getEmail());
            System.out.println("CNPJ : " + loja.getCnpj());
            System.out.println("-------------------------");
        }
    }


}
