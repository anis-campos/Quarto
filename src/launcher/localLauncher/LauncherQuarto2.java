/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Locale;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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
        frame.setResizable(true);


        JPanel generalPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardLayout.setHgap(10);
        cardLayout.setVgap(10);
        JPanel menu = new JPanelMenu();
        JPanel parametres = new JPanelParametres();

        generalPanel.setLayout(cardLayout);
        generalPanel.setVisible(true);
        generalPanel.setEnabled(true);
        generalPanel.setName("generalPanel");
        menu.setVisible(true);
        menu.setEnabled(true);
        menu.setName("menu");
        parametres.setVisible(true);
        parametres.setEnabled(true);
        parametres.setName("parametres");

        generalPanel.add("menu", menu);
        generalPanel.add("parametres", parametres);

        cardLayout.show(generalPanel, "menu");

        frame.setContentPane(generalPanel);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);

        frame.setVisible(true);
    }
}
