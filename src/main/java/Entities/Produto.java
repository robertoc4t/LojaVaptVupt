package Entities;

public class Produto implements ItemCarrinho {
    private String categoria;
    private String nome;
    private double preco;
    private String descricao;

    public Produto(String categoria, String nome, double preco, String descricao) {
        this.categoria = categoria;
        this.nome = nome;
        this.preco = preco;
        this.descricao = descricao;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public String getDescricao() {
        return descricao;
    }



    }
