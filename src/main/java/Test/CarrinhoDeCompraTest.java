package Test;

import Entities.CarrinhoDeCompras;
import Entities.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarrinhoDeComprasTest {

    private CarrinhoDeCompras carrinho;
    private Produto produto1;
    private Produto produto2;

    @BeforeEach
    void setUp() {
        carrinho = new CarrinhoDeCompras();
        produto1 = new Produto("Smartphone", "Smartphone Samsung", 1200.00);
        produto2 = new Produto("Notebook", "Notebook Dell", 2500.00);
    }

    @Test
    void testAdicionarProduto() {
        carrinho.adicionarProduto(produto1);
        assertEquals(1, carrinho.getCarrinho().size());
    }

    @Test
    void testRemoverProduto() {
        carrinho.adicionarProduto(produto1);
        carrinho.removerProduto(produto1);
        assertEquals(0, carrinho.getCarrinho().size());
    }

    @Test
    void testCalcularValorTotal() {
        carrinho.adicionarProduto(produto1);
        carrinho.adicionarProduto(produto2);
        double total = carrinho.calcularValorTotal();
        assertEquals(3700.00, total);
    }
}

