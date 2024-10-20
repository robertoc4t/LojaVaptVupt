package GUI;
import Entities.Produto;
import Util.ArquivoProdutos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static java.lang.Double.parseDouble;

public class VaptVuptGUIAdmin extends JFrame {

    public static final String LOCAL_ARQUIVO = "src/main/resources/produtos.txt";

    public VaptVuptGUIAdmin() {
        // Configuração da janela
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Centraliza a janela

        // Responsividade
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Criando painel superior
        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new GridLayout(5, 2, 10, 10));// 5 linhas e 2 colunas com espaçamento
        JPanel painelInferior = new JPanel();
        painelInferior.setLayout(new GridLayout(5, 2, 10, 10));



        // Componentes
        JLabel labelCategoria = new JLabel("Categoria:");
        JTextField categoria = new JTextField(20);  // Define o tamanho do campo de texto

        JLabel labelNome = new JLabel("Nome:");
        JTextField nome = new JTextField(20);

        JLabel labelPreco = new JLabel("Preço:");
        JTextField preco = new JTextField(20);

        JLabel labelDescricao = new JLabel("Descrição:");
        JTextField descricao = new JTextField(20);

        JButton btnUser = new JButton("Modo User");
        JButton btnCadastrar = new JButton("Cadastrar Produto");

        JButton btnRemoverModal = new JButton("Remover Produto");

        // Adicionando os componentes ao painel
        painelSuperior.add(labelCategoria);
        painelSuperior.add(categoria);

        painelSuperior.add(labelNome);
        painelSuperior.add(nome);

        painelSuperior.add(labelPreco);
        painelSuperior.add(preco);

        painelSuperior.add(labelDescricao);
        painelSuperior.add(descricao);

        painelSuperior.add(btnCadastrar);
        painelInferior.add(btnUser);

        painelSuperior.add(btnRemoverModal);

        // Adicionando o painel ao JFrame
        add(painelSuperior, BorderLayout.NORTH);
        add(painelInferior, BorderLayout.SOUTH);

        // Funcionalidades dos botões
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VaptVuptGUI user = new VaptVuptGUI();
                dispose(); // Fecha a janela atual
            }
        });

        btnCadastrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                // Lógica para cadastrar produto (Exemplo básico de exibição de dados)
                String categoriaTexto = categoria.getText();
                String nomeTexto = nome.getText();
                double precoTexto = parseDouble(preco.getText());
                String descricaoTexto = descricao.getText();
                Produto produto = new Produto(categoriaTexto, nomeTexto, precoTexto, descricaoTexto);

                
                List<Produto> listaProdutos = ArquivoProdutos.carregarDados(LOCAL_ARQUIVO);
                listaProdutos.add(produto);
                ArquivoProdutos.salvarDados(LOCAL_ARQUIVO, listaProdutos);

                JOptionPane.showMessageDialog(null, "Produto cadastrado:\nCategoria: " + categoriaTexto
                        + "\nNome: " + nomeTexto + "\nPreço: " + precoTexto + "\nDescrição: " + descricaoTexto);

                // Aqui você pode adicionar lógica para salvar o produto em um arquivo ou lista
            }
        });

        btnRemoverModal.addActionListener(e -> removerProdutoDoCadastroModal());

        // Tornando a janela visível após adicionar todos os componentes
        setVisible(true);
    }

    private void removerProdutoDoCadastroModal(){

        List<Produto> produtosCadastrados = ArquivoProdutos.carregarDados(LOCAL_ARQUIVO);

        if(produtosCadastrados.size() > 0){

            String[] listaProdutosCadastrados = produtosCadastrados.stream().map(produto -> produto.getNome() + " - R$ " + produto.getPreco()).toArray(String[]::new);

            String produtoSelecionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o produto que deseja remover:",
                "Remover Produto do Cadastro",
                JOptionPane.PLAIN_MESSAGE,
                null,
                listaProdutosCadastrados,
                listaProdutosCadastrados[0]
            );

            if (produtoSelecionado != null) {
                // Obtém o produto selecionado a partir do nome
                Produto produtoParaRemover = produtosCadastrados.stream()
                        .filter(produto -> produtoSelecionado.startsWith(produto.getNome()))
                        .findFirst().orElse(null);
    
                if (produtoParaRemover != null) {
                    // Remove o produto do carrinho
                    produtosCadastrados.remove(produtoParaRemover);
                    ArquivoProdutos.salvarDados(LOCAL_ARQUIVO, produtosCadastrados);
                    JOptionPane.showMessageDialog(this, "Produto removido do cadastro com sucesso!");
                }
            }

        }
            
    }

    public static void main(String[] args) {
        // Executa a aplicação
        new VaptVuptGUIAdmin();
    }
}

