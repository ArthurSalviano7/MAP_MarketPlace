import java.util.ArrayList;

public class MarketPlace {

	public static void main(String[] args) {
		
		System.out.print("Hello World!");
		objTeste ObjDeTeste = new objTeste("Pedro", 1.56);
		objTeste ObjDeTeste2 = new objTeste("João", 1.3);
		ArrayList<Object> lista = new ArrayList<Object>();
		lista.add(ObjDeTeste);
		lista.add(ObjDeTeste2);
		
		Serializacao.gravarArquivo(lista, "arquivoObjetos");
	}

}
