package Aplication;

import GUI.KatchauGUI;

import javax.swing.*;

import Exceptions.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            KatchauGUI katchauGUI = new KatchauGUI();
        });
    }
}
