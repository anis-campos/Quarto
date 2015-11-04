/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import controlleur.ControllerLocal;
import java.util.Observer;
import javax.swing.*;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import view.QuartoGUI;


/**
 * @author Anis
 */
public class LauncherQuarto {

    public static void main(String[] args) {

        //Dimension dim = new Dimension(992, 540);
        
        Parametre p = new Parametre(true, true, true, true, true);
        Joueur j1 = new Joueur("Joueur 1", false,NumeroJoueur.J1);
        Joueur j2 = new Joueur("Joueur 2", false,NumeroJoueur.J2);
        Partie partie = new Partie(p, j1, j2);
        
        ControllerLocal controllerLocal = new ControllerLocal(partie);

        JPanel panel = new QuartoGUI(controllerLocal);
        
        controllerLocal.addObserver((Observer) panel);

        panel.setLocation(600, 10);
        //frame.setPreferredSize(dim);
        panel.setVisible(true);
    }
}
