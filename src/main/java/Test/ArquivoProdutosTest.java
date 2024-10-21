package Test;

import Util.ArquivoProdutos;
import Entities.Produto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArquivoProdutosTest {

    @Test
    void testCarregarDados() {
        List<Produto> produtos = ArquivoProdutos.carregarDados("src/main/resources/produtos.txt");
        assertNotNull(produtos);
        assertTrue(produtos.size() > 0);
    }
}
