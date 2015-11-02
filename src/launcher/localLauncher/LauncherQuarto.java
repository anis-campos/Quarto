/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.launcher.localLauncher;

import javax.swing.*;

import src.view.QuartoGUI;

/**
 * @author Anis
 */
public class LauncherQuarto {

    public static void main(String[] args) {

        //Dimension dim = new Dimension(992, 540);


        JFrame frame = new QuartoGUI();

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        //frame.setPreferredSize(dim);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
