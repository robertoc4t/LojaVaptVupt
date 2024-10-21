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
    public JList<String> listProdutos;
    public CarrinhoDeCompras carrinho;
    public  JTextArea textAreaDescricao;
    public  JComboBox<String> comboBoxFiltro;
    public  List<Produto> todosProdutos;
    public  List<String> produtosExibidos;

    public VaptVuptGUI() {
        carrinho = new CarrinhoDeCompras();
        todosProdutos = ArquivoProdutos.carregarDados("src/main/resources/produtos.txt");
        produtosExibidos = todosProdutos.stream()
                .map(produto -> produto.getNome() + " - R$ " + String.format("%.2f", produto.getPreco()))
                .collect(Collectors.toList());

        setTitle("Katchau! - Loja de Eletrônicos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setVisible(true);

        listProdutos = new JList<>(produtosExibidos.toArray(new String[0]));
        JButton btnAdicionar = new JButton("Adicionar ao carrinho");
        JButton btnRemover = new JButton("Remover do carrinho");
        JButton btnCalcularTotal = new JButton("Finalizar compra");
        JButton btnVerCarrinho = new JButton("Ver Carrinho");
        JButton btnModoAdm= new JButton("Modo ADM");
        textAreaDescricao = new JTextArea();
        textAreaDescricao.setEditable(false);

        ImageIcon imagenIcon = new ImageIcon("src/main/resources/imagen.png");

        comboBoxFiltro = new JComboBox<>();
        comboBoxFiltro.addItem("Todos");
        comboBoxFiltro.addItem("Smartphone");
        comboBoxFiltro.addItem("TV");
        comboBoxFiltro.addItem("Computador");
        comboBoxFiltro.addItem("Console");
        comboBoxFiltro.addItem("Notebook");

        JPanel painelSuperior = new JPanel(new BorderLayout());
        JPanel painelInferior = new JPanel(new FlowLayout());

        painelSuperior.add(new JScrollPane(listProdutos), BorderLayout.CENTER);
        painelSuperior.add(btnAdicionar, BorderLayout.SOUTH);
        painelSuperior.add(btnRemover, BorderLayout.NORTH);
        painelSuperior.add(comboBoxFiltro, BorderLayout.EAST);
        painelInferior.add(btnCalcularTotal);
        painelInferior.add(btnVerCarrinho);
        painelInferior.add(btnModoAdm);

        add(painelSuperior, BorderLayout.NORTH);
        add(textAreaDescricao, BorderLayout.CENTER);
        add(painelInferior, BorderLayout.SOUTH);

        btnAdicionar.addActionListener(e -> adicionarProdutoAoCarrinho());

        btnRemover.addActionListener(e -> removerProdutoDoCarrinhoGUI());

        btnCalcularTotal.addActionListener(e -> calcularValorTotal());

        btnVerCarrinho.addActionListener(e -> exibirCarrinho());

        comboBoxFiltro.addActionListener(e -> filtrarProdutos());

        listProdutos.setSelectedIndex(0);

        listProdutos.addListSelectionListener(e -> exibirDescricaoProduto());

        btnModoAdm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Chama a nova janela (classe NovaJanela)
                VaptVuptGUIAdmin adm = new VaptVuptGUIAdmin();
                dispose();
            }
        });

        exibirDescricaoProduto();

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);


    }

    public void adicionarProdutoAoCarrinho() {
        int index = listProdutos.getSelectedIndex();
        if (index != -1) {
            Produto produtoSelecionado = todosProdutos.get(index);
            carrinho.adicionarProduto(produtoSelecionado);
            JOptionPane.showMessageDialog(this, "Produto adicionado ao carrinho com sucesso!");
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um produto da lista para adicionar ao carrinho!");
        }
    }

    public void removerProdutoDoCarrinhoGUI() {
        Map<Produto, Integer> itensCarrinho = carrinho.getCarrinho();

        if (itensCarrinho.isEmpty()) {
            JOptionPane.showMessageDialog(this, "O carrinho está vazio!");
            return;
        }

        String[] produtosCarrinho = itensCarrinho.entrySet().stream()
                .map(entry -> entry.getKey().getNome() + " - Quantidade: " + entry.getValue())
                .toArray(String[]::new);
        String produtoSelecionado = (String) JOptionPane.showInputDialog(
                this,
                "Selecione o produto que deseja remover:",
                "Remover Produto do Carrinho",
                JOptionPane.PLAIN_MESSAGE,
                null,
                produtosCarrinho,
                produtosCarrinho[0]);

        if (produtoSelecionado != null) {
            Produto produtoParaRemover = itensCarrinho.keySet().stream()
                    .filter(produto -> produtoSelecionado.startsWith(produto.getNome()))
                    .findFirst().orElse(null);

            if (produtoParaRemover != null) {
                carrinho.removerProduto(produtoParaRemover);
                JOptionPane.showMessageDialog(this, "Produto removido do sistema com sucesso!");
            }
        }
    }
    private void calcularValorTotal() {
        double valorTotal = carrinho.calcularValorTotal();
        JOptionPane.showMessageDialog(this, "Valor total da compra: R$ " + String.format("%.2f", valorTotal));
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

    public List<Produto> filtrarProdutos() {
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
        return null;
    }

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
