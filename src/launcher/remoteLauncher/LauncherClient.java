/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package launcher.remoteLauncher;

import Network.socket.client.Client;
import controlleur.AbstractController;
import controlleur.ControllerDistant;
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
import static launcher.remoteLauncher.LauncherServer.IP_SERVER;
import static launcher.remoteLauncher.LauncherServer.PORT;
import model.Joueur;
import model.NumeroJoueur;
import model.Parametre;
import model.Partie;
import view.GUIResolutionTool;
import view.JPanelMenu;
import view.JPanelParametres;
import view.JPanelQuarto;
import view.fenetrePrincipale;

/**
 *
 * @author Anis
 */
public class LauncherClient {

    public static void main(String[] args) {

        Parametre p = new Parametre(Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE);
        Joueur j1 = new Joueur("Serveur", Boolean.FALSE, NumeroJoueur.J1);
        Joueur j2 = new Joueur("Client", Boolean.FALSE, NumeroJoueur.J2);

        Partie partie = new Partie(p, j1, j2);
        ControllerDistant controllerLocal = new ControllerDistant(ControllerDistant.Type.CLIENT, partie);

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

        JPanel panel = new JPanelQuarto(controllerLocal, GUIResolutionTool.getSizeOfCase());
        controllerLocal.addObserver((Observer) panel);
        panel.setName("jeu");//Important

        generalPanel.add("jeu", panel);

        cardLayout.show(generalPanel, "jeu");

        frame.setTitle("MODE CLIENT");
        frame.setContentPane(generalPanel);

        frame.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
        frame.setVisible(true);
    }

    public static boolean Connexion(AbstractController controller) {
        try {

            System.out.println("Demande de connexion");
            Socket socket = new Socket(IP_SERVER, PORT);
            System.out.println("Connexion tablie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connect

            Thread t1 = new Thread(new Client(socket, controller));
            t1.start();

            return true;

        } catch (UnknownHostException e) {
            System.err.println("Impossible de se connecter  l'adresse :" + IP_SERVER);
        } catch (IOException e) {
            System.err.println("Aucun serveur à l'écoute du port : " + PORT);
        }

        return false;
    }
}
