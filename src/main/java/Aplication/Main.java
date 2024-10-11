package Aplication;

import GUI.VaptVuptGUI;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VaptVuptGUI vaptVuptGUI = new VaptVuptGUI();
        });
    }
}
