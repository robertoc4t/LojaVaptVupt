package Entities;

import Exceptions.*;

public interface ProdutoInterface {

    public void CadastraProduto(String categoria, String nome, double preco, String descricao) throws ItemJaExistenteException;

    public void removerProduto(String descricao)throws ProdutoNaoAchadoException;

    public void alterarProduto(String descricao) throws ProdutoNaoAchadoException;

    public boolean existeProduto(Produto produto);

    public Produto pesquisarPelaDescricao(String descricao) throws ProdutoNaoAchadoException;


}
