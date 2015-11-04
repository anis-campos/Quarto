/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.util.Observer;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import view.JPanelJeu;
import view.JPanelMenu;
import view.JPanelParametres;
import view.fenetrePrincipale;

/**
 *
 * @author timotheetroncy
 */
public class LauncherQuarto2 {

    public static void main(String[] args) {

        JFrame frame = new fenetrePrincipale();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.setResizable(true);
        frame.pack();
        frame.setVisible(true);

        JPanel generalPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardLayout.setHgap(10);
        cardLayout.setVgap(10);
        JPanel menu = new JPanelMenu();
        JPanel jeu = new JPanelJeu();
        JPanel parametres = new JPanelParametres();

        generalPanel.setLayout(cardLayout);
        generalPanel.setVisible(true);
        generalPanel.setEnabled(true);
        menu.setVisible(true);
        menu.setEnabled(true);
        jeu.setVisible(true);
        jeu.setEnabled(true);
        parametres.setVisible(true);
        parametres.setEnabled(true);

        generalPanel.add("menu", menu);
        generalPanel.add("jeu", jeu);
        generalPanel.add("parametres", parametres);

        cardLayout.show(generalPanel, "menu");

        frame.setContentPane(generalPanel);
        frame.repaint();

    }
}
