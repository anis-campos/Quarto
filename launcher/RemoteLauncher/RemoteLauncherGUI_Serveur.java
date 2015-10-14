package launcher.RemoteLauncher;

import controller.ChessGameControlers;
import controller.controllerRemote.ChessGameControler;
import java.awt.Color;
import java.awt.Dimension;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observer;
import javax.swing.JFrame;

import socket.server.Accepter_connexion;
import model.observable.ChessGame;
import view.ChessGameGUI;

/**
 * @author francoise.perrin Lance le serveur
 *
 */
public class RemoteLauncherGUI_Serveur {

    public static final int PORT = 5000;
    public static String IP_SERVER = "127.0.0.1";

    public static void main(String[] args) {

        ChessGame chessGame;
        ChessGameControlers chessGameControler;
        JFrame frame;
        Dimension dim;

        dim = new Dimension(800, 800);

        chessGame = new ChessGame();
        chessGameControler = new controller.controllerRemote.ChessGameControler(chessGame,ChessGameControler.Type.SERVER);

        if (!Connexion(chessGameControler)) {
            return;
        }

        
        frame = new ChessGameGUI("Jeu d'échec", chessGameControler, dim);
        chessGame.addObserver((Observer) frame);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation(600, 10);
        frame.setPreferredSize(dim);
        
        frame.setTitle("LE SERVEUR");
        
        frame.pack();
        frame.setVisible(true);

    }

    public static boolean Connexion(ChessGameControlers controller) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Le serveur est à l'écoute du port " + PORT);
            Thread connectionServer = new Thread(new Accepter_connexion(serverSocket, controller));
            connectionServer.start();
            return true;
        } catch (IOException e) {
            System.err.println("Le port " + PORT + " est déjà utilisé !");
        }

        return false;
    }

}
