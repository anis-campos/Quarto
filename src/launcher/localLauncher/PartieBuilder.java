/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import controlleur.ControllerLocal;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Joueur;
import model.Parametre;
import model.Partie;
import view.JPanelQuarto;

/**
 *
 * @author timotheetroncy
 */
public class PartieBuilder {
    
    public static void buildPartie(Parametre p, Joueur j1, Joueur j2, JPanel targetPanel){
        Partie partie = new Partie(p, j1, j2);
        ControllerLocal controllerLocal = new ControllerLocal(partie);
        JPanel panel = new JPanelQuarto(controllerLocal,new Dimension(90,90));
        controllerLocal.addObserver((Observer) panel);
        panel.setName("jeu");//Important

        CardLayout cl = (CardLayout) targetPanel.getParent().getLayout();

        
        Component[] components = targetPanel.getParent().getComponents();
        for (Component c : components) {
            //on remove le component jeu
            if (c.getName().equals("jeu")) {
                cl.removeLayoutComponent(c);
                targetPanel.getParent().remove(c);
            }
            //On vient activer le bouton Continuer Partie
            if (c.getName().equals("menu")) {
                JPanel menuPanel = (JPanel) c;
                Component[] menuComps = menuPanel.getComponents();
                for (Component button : menuComps) {
                    if (button.getName().equals("jButtonContinuer")) {
                        button.setEnabled(true);
                    }
                }
            }
        }
        
        targetPanel.getParent().add("jeu", panel);

        cl.show(targetPanel.getParent(), "jeu");
        
        JFrame frame = (JFrame) SwingUtilities.getRoot(targetPanel);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }
    
}
