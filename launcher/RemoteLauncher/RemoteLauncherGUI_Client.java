package launcher.RemoteLauncher;

import java.awt.Dimension;
import java.util.Observer;

import javax.swing.JFrame;

import controller.ChessGameControlers;
import controller.controllerRemote.ChessGameControler;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;
import static launcher.RemoteLauncher.RemoteLauncherGUI_Serveur.IP_SERVER;
import model.observable.ChessGame;
import view.ChessGameGUI;
import static launcher.RemoteLauncher.RemoteLauncherGUI_Serveur.PORT;
import socket.client.Client;

/**
 * @author francoise.perrin Lance l'exécution d'un jeu d'échec en mode
 * graphique. La vue (ChessGameGUI) observe le modèle (ChessGame) les échanges
 * passent par le contrôleur (ChessGameControlers)
 *
 */
public class RemoteLauncherGUI_Client {

    /**
     * @param args
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        ChessGame chessGame;
        ChessGameControlers chessGameControler;
        JFrame frame;
        Dimension dim;

        dim = new Dimension(800, 800);

        chessGame = new ChessGame();
        chessGameControler = new controller.controllerRemote.ChessGameControler(chessGame, ChessGameControler.Type.CLIENT);

        if (!Connexion(chessGameControler)) {
            return;
        }

        if (args.length ==0) {
            frame = new ChessGameGUI("Jeu d'échec", chessGameControler, dim);
            chessGame.addObserver((Observer) frame);

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocation(600, 10);
            frame.setPreferredSize(dim);

            frame.setTitle("LE CLIENT");

            frame.pack();
            frame.setVisible(true);
        }

    }

    public static boolean Connexion(ChessGameControlers chessGameControler) {

        try {


            System.out.println("Demande de connexion");
            Socket socket = new Socket(IP_SERVER, PORT);
            System.out.println("Connexion tablie avec le serveur, authentification :"); // Si le message s'affiche c'est que je suis connect

            Thread t1 = new Thread(new Client(socket, chessGameControler));
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
