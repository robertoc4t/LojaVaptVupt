package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class VaptVuptGUIAdmin extends JFrame implements Cloneable {
    public VaptVuptGUIAdmin() {
        // Configuração da janela
        setTitle("Admin");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Centraliza a janela


        // Responsividade
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        JPanel panelSuperior = new JPanel();

        // Componentes
        JButton btnUser = new JButton("Modo User");
        panelSuperior.add(btnUser);

        // Adicionando o painel ao JFrame
        add(panelSuperior, BorderLayout.NORTH); // Use o layout padrão

        //funcionalidade
        btnUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                VaptVuptGUI user = new VaptVuptGUI();
                dispose();
            }
        });


        // Tornando a janela visível após adicionar todos os componentes
        setVisible(true);
    }
}
