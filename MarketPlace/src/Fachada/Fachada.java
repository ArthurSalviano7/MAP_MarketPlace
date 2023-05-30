package Fachada;

import entidades.Comprador;
import entidades.Loja;
import java.util.ArrayList;
import java.util.List;


public class Fachada {
    private static ArrayList<Comprador> listaCompradores = new ArrayList<>();
    private static ArrayList<Loja> listaLojas = new ArrayList<>();
	private static int id = 1; //Id para Lojas e compradores
	private static ArrayList<Object> listaDeObjetos = new ArrayList<>();
		
    public Fachada(){}

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
           // exibirMenuComprador(); // Incompleto
        }
        else if(lojaAutenticada){
          //  exibirMenuLoja(); // Incompleto
        }
        else{
            System.out.println("Usuário não cadastrado!!");
        }
    }//Fim do método autenticar()

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
    
    //listarProdutos()
    
    //Método para recuperar o Id de acordo com os objetos do arquivo
    public static void recuperarID() {
		if (!listaDeObjetos.isEmpty()) {
			Object ultimoObjeto = listaDeObjetos.get(listaDeObjetos.size() - 1);
			if (ultimoObjeto instanceof Loja) {
				Loja ultimaLoja = (Loja) ultimoObjeto;
				id = ultimaLoja.getId() + 1;
			} else if (ultimoObjeto instanceof Comprador) {
				Comprador ultimoComprador = (Comprador) ultimoObjeto;
				id = ultimoComprador.getId() + 1;
			}
		}
	}//Fim do método recuperarID

    
    private static void adicionarComprador(Comprador comprador) {
    	listaCompradores.add(comprador);
    }
    
    public static void cadastrarComprador(String nome, String email, String senha, String cpf, String endereco) {
    	Comprador comprador = new Comprador(id, nome, email, senha, "Comprador", cpf, endereco);
    	adicionarComprador(comprador);
    }

    private static void adicionarLojas(Loja loja) {
        listaLojas.add(loja);
    }

    public static void cadastrarlojas(String nome, String email, String senha, String cnpj, String endereco) {
        Loja lojas = new Loja(id, nome, email, senha, tipoUsuario:"Lojas", cnpj, endereco);
        adicionarLojas(lojas);
    }
 }