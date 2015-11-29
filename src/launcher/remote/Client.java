/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remote;

import Network.Client_Serveur.*;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import static Network.Client_Serveur.LauncherServer.IP_SERVER;
import static Network.Client_Serveur.LauncherServer.PORT;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import view.GUIResolutionTool;
import view.JPanelMenuLocal;
import view.JPanelParametresLocal;
import view.JPanelQuarto;
import view.fenetrePrincipale;

/**
 *
 * @author Anis
 */
public class LauncherClient {

    public static void main(String[] args) {

        
    }

    public static boolean Connexion(ControllerDistant controller) {
        return false;
    }
}
