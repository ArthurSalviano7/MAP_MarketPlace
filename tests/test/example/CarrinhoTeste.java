package test.example;
import entidades.Carrinho;
import entidades.Comprador;
import entidades.Produto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CarrinhoTeste {
	
	private Carrinho carrinho;
    private Produto produto; 
    private Comprador comprador;
    
    @BeforeEach
    public void setupCarrinho() {
    	carrinho = new Carrinho();
    	produto = new Produto(1, "Produto Teste", 10, 40, "Vestuario", "Adidas");
    	comprador = new Comprador(1, "Nome", "email@exemplo.com", "senha", "Testes", "123456789", "endereco");
    }
    
    @Test
    public void testAdicionarProdutoCarrinho() {    	
    	carrinho.adicionarProduto(produto, 1);
    	Assertions.assertEquals(1, carrinho.getListaProdutos().size());
    	Assertions.assertEquals(produto.getDescricao(), carrinho.getListaProdutos().get(0).getDescricao());
    }

    @Test
    public void testRemoverProdutoCarrinho() {
    	Produto produto = new Produto(1, "Produto Teste", 10, 39.99, "Vestuario", "Adidas");

    	comprador.getCarrinho().adicionarProduto(produto,1);

    	comprador.getCarrinho().removerProduto(1);
    	Assertions.assertEquals(0, comprador.getCarrinho().getListaProdutos().size());
    }
    
    @Test
    public void testRemoverProdutoInexistente() {
    	Produto produto = new Produto(1, "Produto Teste", 10, 39.99, "Vestuario", "Adidas");

    	comprador.getCarrinho().adicionarProduto(produto,1);

    	comprador.getCarrinho().removerProduto(5);
    	Assertions.assertEquals(1, comprador.getCarrinho().getListaProdutos().size());
    	Assertions.assertEquals(produto.getDescricao(), comprador.getCarrinho().getListaProdutos().get(0).getDescricao());
    }
    
    @Test
    public void testCalcularValorTotalCarrinho() {
    	Produto produto2 = new Produto(2, "Produto 2", 5, 20, "Livros", "Marca");

    	carrinho.adicionarProduto(produto, 1);
    	carrinho.adicionarProduto(produto2, 1);

    	double valorTotal = carrinho.calcularTotalComBeneficio(); // 40 + 20
    	Assertions.assertEquals(60, valorTotal);
    }
    
    @Test
    public void testCalcularValorTotalVariosProdutosEQuantidades() {
    	Produto produto2 = new Produto(2, "Produto 2", 10, 20.50, "Livros", "Marca");
    	Produto produto3 = new Produto(2, "Produto 3", 24, 1.50, "Eletronico", "Marca");
    	Produto produto4 = new Produto(2, "Produto 4", 16, 25, "Limpeza", "Marca");


    	carrinho.adicionarProduto(produto, 2); //2*40 +
    	carrinho.adicionarProduto(produto2, 5); //5*20.50 +
    	carrinho.adicionarProduto(produto3, 3); //3*1.50 +
    	carrinho.adicionarProduto(produto4, 1); //25 = 212

    	double valorTotal = carrinho.calcularTotalComBeneficio();
    	Assertions.assertEquals(212, valorTotal);
    }

    @Test
    public void testEsvaziarCarrinho() {
    	Carrinho carrinho = new Carrinho();
    	Produto produto2 = new Produto(2, "Produto 2", 5, 20.25, "Livros", "Marca");

    	carrinho.adicionarProduto(produto, 1);
    	carrinho.adicionarProduto(produto2, 1);

    	carrinho.limparCarrinho();
    	Assertions.assertEquals(0, carrinho.getListaProdutos().size());

		private Carrinho carrinho;

		@BeforeEach
		public void setup() {
			carrinho = new Carrinho();
		}

		@Test
		public void testCalcularTotalSemBeneficio() {
			// Arrange
			Produto produto1 = new Produto(1, "Produto 1", 15.0);
			Produto produto2 = new Produto(2, "Produto 2", 30.0);

			carrinho.adicionarProduto(produto1, 2);
			carrinho.adicionarProduto(produto2, 3);

			// Act
			double total = carrinho.calcularTotalSemBeneficio();

			// Assert
			assertEquals(110.0, total);
		}

		@Test
		public void testCalcularTotalComBeneficio() {
			// Arrange
			Produto produto1 = new Produto(1, "Produto 1", 10.0);
			Produto produto2 = new Produto(2, "Produto 2", 45.0);

			carrinho.adicionarProduto(produto1, 2);
			carrinho.adicionarProduto(produto2, 3);

			// Act
			double total = carrinho.calcularTotalComBeneficio();

			// Assert
			assertEquals(100.0, total);
		}
	}
    

