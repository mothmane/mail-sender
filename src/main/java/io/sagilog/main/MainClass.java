package io.sagilog.main;


import io.sagilog.ui.SwingMailFrame;

import javax.swing.*;

public class MainClass {

    public static void main(String[] args) {
            // set look and feel to system dependent
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new SwingMailFrame().setVisible(true);
                }
            });
        }
}

