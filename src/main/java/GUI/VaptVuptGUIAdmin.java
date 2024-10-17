package GUI;
import Entities.Produto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Double.parseDouble;

public class VaptVuptGUIAdmin extends JFrame {
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




                JOptionPane.showMessageDialog(null, "Produto cadastrado:\nCategoria: " + categoriaTexto
                        + "\nNome: " + nomeTexto + "\nPreço: " + precoTexto + "\nDescrição: " + descricaoTexto);

                // Aqui você pode adicionar lógica para salvar o produto em um arquivo ou lista
            }
        });

        // Tornando a janela visível após adicionar todos os componentes
        setVisible(true);
    }

    public static void main(String[] args) {
        // Executa a aplicação
        new VaptVuptGUIAdmin();
    }
}

