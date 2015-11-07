/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import java.awt.AWTException;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import view.JPanelMenu;
import view.JPanelParametres;
import view.JPanelQuarto;
import view.fenetrePrincipale;

/**
 *
 * @author Anis
 */
public class LauncherQuartoTest {
     public static void main(String[] args) {

        JFrame frame = new fenetrePrincipale();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
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
        generalPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        generalPanel.setAlignmentY(Component.CENTER_ALIGNMENT);
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
        
        testGUI(generalPanel);
        
    }
     
      public static void testGUI(JPanel contentPane) {
        CardLayout cl = (CardLayout) contentPane.getLayout();
        JPanel menu = (JPanel) contentPane.getComponent(0);
        JButton commencerPartie = (JButton) menu.getComponent(0);
        commencerPartie.doClick();
        JPanelQuarto jeu = (JPanelQuarto) contentPane.getComponent(2);

        try {
            jeu.test();
        } catch (AWTException ex) {
            Logger.getLogger(LauncherQuartoTest.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Thread.sleep(100000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LauncherQuartoTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
     
}
