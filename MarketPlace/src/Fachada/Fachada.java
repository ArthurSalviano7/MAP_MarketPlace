package Fachada;

import entidades.Comprador;
import entidades.Loja;
import java.util.ArrayList;
import java.util.List;


public class Fachada {
    public static ArrayList<Comprador> listaCompradores = new ArrayList<>();
    private static ArrayList<Loja> listaLojas = new ArrayList<>();
	private static int id = 1; //Id para Lojas e compradores
	public static ArrayList<Object> listaDeObjetos = new ArrayList<>();
		
    public Fachada(){}

    public String autenticar(String email, String senha) {

        for (Comprador comprador : listaCompradores){ 
            if(comprador.getEmail().equals(email) && comprador.getSenha().equals(senha)){
                return "Usuário autenticado com sucesso!!";
            }
        }

        for(Loja loja : listaLojas){
            if(loja.getEmail().equals(email) && loja.getSenha().equals(senha)){
                return "Usuário autenticado com sucesso!!";
            }
        }

        return "Usuário não cadastrado!!";
    }//Fim do método autenticar()

    public void listarCompradores(){
        for(Comprador comprador : listaCompradores){
            System.out.println("Nome : " + comprador.getNome());
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
    public static int recuperarID() {
		if (!listaDeObjetos.isEmpty()) { //Se a lista não estiver vazia
			Object ultimoObjeto = listaDeObjetos.get(listaDeObjetos.size() - 1);
			if (ultimoObjeto instanceof Loja) {
				Loja ultimaLoja = (Loja) ultimoObjeto;
				id = ultimaLoja.getId() + 1;
				return id;
			} else if (ultimoObjeto instanceof Comprador) {
				Comprador ultimoComprador = (Comprador) ultimoObjeto;
				 id = ultimoComprador.getId() + 1;
				 return id;
			}
		}
		id = 1;
		return id;
	}//Fim do método recuperarID

    public static void cadastrarComprador(String nome, String email, String senha, String cpf, String endereco) {
    	Comprador comprador = new Comprador(id, nome, email, senha, "Comprador", cpf, endereco);
    	comprador.cadastrar(listaCompradores);
    	id++;
    }

    public static void cadastrarLoja(String nome, String email, String senha, String cnpj, String cpf, String endereco) {
        Loja loja = new Loja(id, nome, email, senha,"Loja", cnpj, cpf, endereco);
        loja.cadastrar(listaLojas);
        id++;
    }
    
    public static void atualizarComprador(int id,String nome,String email,String senha,String cpf,String endereco ){
        Comprador novoComprador = new Comprador(id, nome, email, senha, senha, cpf, endereco);
        String dadosAtualizados = novoComprador.atualizar(novoComprador,listaCompradores);

        System.out.println(dadosAtualizados);
    }

    public static void atualizarLoja(int id,String nome,String email,String senha,String cnpj,String endereco){
        Loja novaLoja = new Loja(id, nome, email, senha, senha, cnpj, cnpj, endereco);
        String dadosAtualizados = novaLoja.atualizar(novaLoja, listaLojas);

        System.out.println(dadosAtualizados);
    }

    public static String removerComprador (int id,ArrayList<Comprador> listaDeCompradores){ // Revisar
        Comprador comprador = new Comprador();
        comprador.setId(id);
        return comprador.remover(listaCompradores);     
    }

    public static String removerLoja(int id, ArrayList<Loja> listaDeLojas){ // Revisar
        Loja loja = new Loja();
        loja.setId(id);
        return loja.remover(listaDeLojas);
    }
 }