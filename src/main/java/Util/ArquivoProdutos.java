package Util;

import Entities.Produto;
import Exceptions.ProdutoNaoAchadoException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArquivoProdutos {
    public static List<Produto> carregarDados(String arquivo) {
        List<Produto> produtos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(arquivo))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                if (dados.length >= 4) {
                    String categoria = dados[0];
                    String nome = dados[1];
                    double preco = Double.parseDouble(dados[2]);
                    String descricao = dados[3];
                    Produto produto = new Produto(categoria, nome, preco);
                    produtos.add(produto);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public static void salvarDados(String arquivo, List<Produto> produtos) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(arquivo))) {
            for (Produto produto : produtos) {
                String linha = produto.getCategoria() + ";" + produto.getNome() + ";" + produto.getPreco() + ";" + produto.getDescricao();
                bw.write(linha);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Produto buscarProdutoPorDescricao(String descricao) throws ProdutoNaoAchadoException {
        for(Produto p : carregarDados(descricao)) {
            if(p.getDescricao().equals(descricao)) {
                return p;
            }
        }
        throw new ProdutoNaoAchadoException("O produto com descricao " + descricao+ " não foi encontrado");
    }


}
