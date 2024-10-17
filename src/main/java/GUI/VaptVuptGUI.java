package GUI;

import Entities.CarrinhoDeCompras;
import Entities.Produto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Util.ArquivoProdutos;

public class VaptVuptGUI extends JFrame {
    private JList<String> listProdutos;
    private CarrinhoDeCompras carrinho;
    private JTextArea textAreaDescricao;
    private JComboBox<String> comboBoxFiltro;
    private List<Produto> todosProdutos;
    private List<String> produtosExibidos;

    public VaptVuptGUI() {
        carrinho = new CarrinhoDeCompras();
        todosProdutos = ArquivoProdutos.carregarDados("src/main/resources/produtos.txt");
        produtosExibidos = todosProdutos.stream()
                .map(produto -> produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()))
                .collect(Collectors.toList());

        // Configurações da janela
        setTitle("Katchau! - Loja de Eletrônicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        // Componentes
        listProdutos = new JList<>(produtosExibidos.toArray(new String[0]));
        JButton btnAdicionar = new JButton("Adicionar ao carrinho");
        JButton btnRemover = new JButton("Remover do carrinho");
        JButton btnCalcularTotal = new JButton("Finalizar compra");
        JButton btnVerCarrinho = new JButton("Ver Carrinho");
        JButton btnModoAdm= new JButton("Modo ADM");
        textAreaDescricao = new JTextArea();
        textAreaDescricao.setEditable(false);

        ImageIcon imagenIcon = new ImageIcon("src/main/resources/imagen.png");

        // Inicialização do JComboBox
        comboBoxFiltro = new JComboBox<>();
        comboBoxFiltro.addItem("Todos");
        comboBoxFiltro.addItem("Smartphone");
        comboBoxFiltro.addItem("TV");
        comboBoxFiltro.addItem("Computador");
        comboBoxFiltro.addItem("Console");
        comboBoxFiltro.addItem("Notebook");

        // Painéis
        JPanel painelSuperior = new JPanel(new BorderLayout());
        JPanel painelInferior = new JPanel(new FlowLayout());



        // Adicionando componentes aos painéis
        painelSuperior.add(new JScrollPane(listProdutos), BorderLayout.CENTER);
        painelSuperior.add(btnAdicionar, BorderLayout.SOUTH);
        painelSuperior.add(btnRemover, BorderLayout.NORTH);
        painelSuperior.add(comboBoxFiltro, BorderLayout.EAST);
        painelInferior.add(btnCalcularTotal);
        painelInferior.add(btnVerCarrinho);
        painelInferior.add(btnModoAdm);



        // Adicionando painéis à janela
        add(painelSuperior, BorderLayout.NORTH);
        add(textAreaDescricao, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        // Ação do botão Adicionar Produto
        btnAdicionar.addActionListener(e -> adicionarProdutoAoCarrinho());

        // Ação do botão Remover Produto
        btnRemover.addActionListener(e -> removerProdutoDoCarrinho());

        // Ação do botão Calcular Valor Total
        btnCalcularTotal.addActionListener(e -> calcularValorTotal());

        // Ação do botão Ver Carrinho
        btnVerCarrinho.addActionListener(e -> exibirCarrinho());

        // Ação da seleção do filtro
        comboBoxFiltro.addActionListener(e -> filtrarProdutos());

        // Selecionar o primeiro produto da lista
        listProdutos.setSelectedIndex(0);

        // Ação da seleção de um produto na lista
        listProdutos.addListSelectionListener(e -> exibirDescricaoProduto());

        // Carregar ADMGUI
        btnModoAdm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama a nova janela (classe NovaJanela)
                VaptVuptGUIAdmin adm = new VaptVuptGUIAdmin();
                dispose();
            }
        });


        // Atualizar a descrição do primeiro produto
        exibirDescricaoProduto();

        // Aplicação da responsividade
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);


    }

    // Método para adicionar um produto ao carrinho
    private void adicionarProdutoAoCarrinho() {
        int index = listProdutos.getSelectedIndex();
        if (index != -1) {
            Produto produtoSelecionado = todosProdutos.get(index);
            carrinho.adicionarProduto(produtoSelecionado);
            JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista para adicionar ao carrinho!");
        }
    }

    // Método para remover um produto do carrinho
    private void removerProdutoDoCarrinho() {
        int index = listProdutos.getSelectedIndex();
        if (index != -1) {
            Produto produtoSelecionado = todosProdutos.get(index);
            carrinho.removerProduto(produtoSelecionado);
            JOptionPane.showMessageDialog(this, "Produto removido do carrinho com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista para remover do carrinho!");
        }
    }

    // Método para calcular o valor total da compra
    private void calcularValorTotal() {
        double valorTotal = carrinho.calcularValorTotal();
        JOptionPane.showMessageDialog(this, "Valor total da compra: R$ " + String.format("%.2f", valorTotal));

        // Exibir a nota fiscal
        StringBuilder notaFiscal = new StringBuilder("Nota Fiscal:\n\n");
        Map<Produto, Integer> itensCarrinho = carrinho.getCarrinho();
        for (Map.Entry<Produto, Integer> entry : itensCarrinho.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            double subtotal = produto.getPreco() * quantidade;
            notaFiscal.append(produto.getNome()).append(" - R$ ").append(String.format("%.2f", produto.getPreco())).append(" x ")
                    .append(quantidade).append(" = R$ ").append(String.format("%.2f", subtotal)).append("\n");
        }
        JOptionPane.showMessageDialog(this, notaFiscal.toString());
        JOptionPane.showMessageDialog(this, "Muito obrigado por comprar com a Katchau!");
    }

    // Método para exibir o carrinho de compras
    private void exibirCarrinho() {
        Map<Produto, Integer> itensCarrinho = carrinho.getCarrinho();
        StringBuilder carrinhoTexto = new StringBuilder("Carrinho de Compras:\n\n");
        for (Map.Entry<Produto, Integer> entry : itensCarrinho.entrySet()) {
            Produto produto = entry.getKey();
            int quantidade = entry.getValue();
            carrinhoTexto.append(produto.getNome()).append(" - R$ ").append(String.format("%.2f", produto.getPreco())).append(" x ")
                    .append(quantidade).append("\n");
        }
        JOptionPane.showMessageDialog(this, carrinhoTexto.toString());
    }

    // Método para filtrar os produtos exibidos na lista de acordo com o filtro selecionado
    private void filtrarProdutos() {
        String filtro = (String) comboBoxFiltro.getSelectedItem();
        if (filtro.equals("Todos")) {
            produtosExibidos = todosProdutos.stream()
                    .map(produto -> produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()))
                    .collect(Collectors.toList());
        } else {
            produtosExibidos = todosProdutos.stream()
                    .filter(produto -> produto.getCategoria().equals(filtro))
                    .map(produto -> produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()))
                    .collect(Collectors.toList());
        }
        listProdutos.setListData(produtosExibidos.toArray(new String[0]));
    }

    // Método para exibir a descrição do produto selecionado
    private void exibirDescricaoProduto() {
        int index = listProdutos.getSelectedIndex();
        if (index != -1) {
            Produto produtoSelecionado = todosProdutos.get(index);
            textAreaDescricao.setText(produtoSelecionado.getDescricao());
        } else {
            textAreaDescricao.setText("");
        }
    }

    private void carregarADMGUI(){
        setVisible(false);
    }

}
