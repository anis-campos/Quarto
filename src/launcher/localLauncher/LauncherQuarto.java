/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.localLauncher;

import controlleur.ControllerLocal;
import java.awt.Dimension;
import javax.swing.*;
import model.Joueur;
import model.Parametre;
import model.Partie;

import view.QuartoGUI;

/**
 * @author Anis
 */
public class LauncherQuarto {

    public static void main(String[] args) {

        //Dimension dim = new Dimension(992, 540);
        
        Parametre p = new Parametre(true, false, true, true, true);
        Joueur j1 = new Joueur("Joueur1", false);
        Joueur j2 = new Joueur("Joueur2", false);
        Partie partie = new Partie(p, j1, j2);
        ControllerLocal controllerLocal = new controlleur.ControllerLocal(partie);

        JFrame frame = new QuartoGUI(controllerLocal);

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        //frame.setPreferredSize(dim);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}
