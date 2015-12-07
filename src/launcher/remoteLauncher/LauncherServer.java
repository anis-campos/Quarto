/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remoteLauncher;

import Network.socket.server.Accepter_connexion;
import controlleur.AbstractController;
import controlleur.ControllerDistant;
import controlleur.ControllerDistant.Type;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ServerSocket;
import java.util.Observer;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
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
public class LauncherServer {

    public static final int PORT = 5555;
    public static String IP_SERVER = "127.0.0.1";

    public static void main(String[] args) {

        Parametre p = new Parametre(true, true, true, true, true, true, false, true, true);
        Joueur j1 = new Joueur("Serveur", false, NumeroJoueur.J1);
        Joueur j2 = new Joueur("Client", false, NumeroJoueur.J2);

        Partie partie = new Partie(p, j1, j2);
        ControllerDistant controllerLocal = new ControllerDistant(Type.SERVER, partie);

        if (!Connexion(controllerLocal)) {
            return;
        }

        JFrame frame = new fenetrePrincipale();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.setResizable(true);

        JPanel generalPanel = new JPanel();
        CardLayout cardLayout = new CardLayout();
        cardLayout.setHgap(0);
        cardLayout.setVgap(0);
        JPanel menu = new JPanelMenuLocal();
        JPanel parametres = new JPanelParametresLocal();

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

        JPanel panel = new JPanelQuarto(controllerLocal, GUIResolutionTool.getSizeOfCase());
        controllerLocal.addObserver((Observer) panel);
        panel.setName("jeu");//Important

        generalPanel.add("jeu", panel);

        cardLayout.show(generalPanel, "jeu");

        frame.setContentPane(generalPanel);

        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
    }

    public static boolean Connexion(AbstractController controller) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Le serveur est à l'écoute du port " + PORT);
            Thread connectionServer = new Thread(new Accepter_connexion(serverSocket, controller));
            connectionServer.start();
            return true;
        } catch (Exception e) {
            System.err.println("Le port " + PORT + " est déjà utilisé !\n" + e.getLocalizedMessage());
        }

        return false;
    }
}
