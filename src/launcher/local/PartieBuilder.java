/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.local;

import IA.Bot;
import controlleur.ControllerLocal;
import java.awt.Dimension;
import java.awt.Toolkit;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.Joueur;
import model.Parametre;
import model.Partie;
import view.GUIResolutionTool;
import view.JPanelQuarto;
import view.PanelGeneral;

/**
 *
 * @author timotheetroncy
 */
public class PartieBuilder {

    public static void buildPartie(Parametre p, Joueur j1, Joueur j2, JPanel targetPanel) {
        Partie partie = new Partie(p, j1, j2);
        ControllerLocal controllerLocal = new ControllerLocal(partie);
        JPanelQuarto jPanelQuarto = new JPanelQuarto(controllerLocal, GUIResolutionTool.getSizeOfCase());
        controllerLocal.addObserver(jPanelQuarto);
        jPanelQuarto.setName("jeu");//Important

        PanelGeneral panelGeneral = (PanelGeneral) targetPanel.getParent();

        panelGeneral.removeJeu();

        panelGeneral.ToggleContinuerPartie(Boolean.TRUE);
        
        panelGeneral.setJeu(jPanelQuarto);
       
        panelGeneral.show("jeu");


        PartieBuilder.repackPartieQuarto(targetPanel);

        //Si la partie est contre bot
        // Le bot est toujours J2
        if (partie.onePlayer()) {
            Bot bot = new Bot(controllerLocal, partie);
            controllerLocal.addObserver(bot);
            controllerLocal.notifierBotPremierTour();
        }

    }

    public static void repackPartieQuarto(JPanel targetPanel) {

        JFrame frame = (JFrame) SwingUtilities.getRoot(targetPanel);
        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
    }

}
