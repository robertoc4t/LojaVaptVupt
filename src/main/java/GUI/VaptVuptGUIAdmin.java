package GUI;

import javax.swing.*;
import java.awt.*;

public class VaptVuptGUIAdmin extends JFrame {
    public VaptVuptGUIAdmin() {
        JFrame frame = new JFrame();
        frame.setTitle("Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(600,300);

        // Resposividade
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setVisible(true);

        JPanel panelSuperior = new JPanel();

        JButton btnUser = new JButton("User");


        panelSuperior.add(btnUser);



        frame.add(panelSuperior);
    }
}
