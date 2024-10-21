package Test;

import Entities.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProdutoTest {

    private Produto produto;

    @BeforeEach
    void setUp() {
        produto = new Produto("Smartphone", "Smartphone Samsung", 1200.00);
    }

    @Test
    void testGetNome() {
        assertEquals("Smartphone", produto.getNome());
    }

    @Test
    void testGetDescricao() {
        assertEquals("Smartphone Samsung", produto.getDescricao());
    }

    @Test
    void testGetPreco() {
        assertEquals(1200.00, produto.getPreco());
    }
}
