package Entities;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoDeCompras {
    private Map<Produto, Integer> carrinho;

    public CarrinhoDeCompras() {
        carrinho = new HashMap<>();
    }

    public void adicionarProduto(Produto produto) {
        int quantidade = carrinho.getOrDefault(produto, 0);
        carrinho.put(produto, quantidade + 1);
    }

    public void removerProduto(Produto produto) {
        int quantidade = carrinho.getOrDefault(produto, 0);
        if (quantidade > 0) {
            if (quantidade == 1) {
                carrinho.remove(produto); // Remove o produto completamente se a quantidade for 1
            } else {
                carrinho.put(produto, quantidade - 1); // Decrementa a quantidade
            }
        }
    }

    public Map<Produto, Integer> getCarrinho() {
        return carrinho;
    }

    public double calcularValorTotal() {
        double valorTotal = 0.0;
        for (Map.Entry<Produto, Integer> entry : carrinho.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            valorTotal += produto.getPreco() * quantidade;
        }
        return valorTotal;
    }
}
