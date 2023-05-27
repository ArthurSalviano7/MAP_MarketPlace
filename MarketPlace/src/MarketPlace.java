import java.util.ArrayList;

import entidades.Loja;
import serializacao.Serializacao;

public class MarketPlace {
	
	private static ArrayList<Object> listaDeObjetos = new ArrayList<>();
	
	public static void main(String[] args) {
		
		ArrayList<Object> listaTemporaria = Serializacao.lerArquivo("arquivoObjetos");
		
		Loja lojaTemp = (Loja)listaTemporaria.get(0);
		lojaTemp.listar();
		
		/*
		Produto produto1 = new Produto(1, "Placa-mãe ", 13, 559.53, "eletronico", "ASUS");
		Produto produto2 = new Produto(1, "Placa de video ", 15, 559.53, "eletronico", "Geforce");
		Produto produto3 = new Produto(1, "livro Harry potter ", 150, 27.90, "livro", "saraiva");
		
		ArrayList<Produto> listaLoja1 = new ArrayList<>();
		Loja loja1 = new Loja("Joao", "emailLoja@email.com", "senha123", "Loja", "cnpj446325",
				"", "endereco, 231", 0, listaLoja1);
		
		loja1.cadastrar(produto1);
		loja1.cadastrar(produto2);
		loja1.cadastrar(produto3);
		
		listaDeObjetos.add(loja1);
		
		Serializacao.gravarArquivo(listaDeObjetos, "arquivoObjetos");
		*/
	}

}
