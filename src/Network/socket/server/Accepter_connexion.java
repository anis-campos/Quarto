package  Network.socket.server;


import controlleur.AbstractController;
import java.io.*;
import java.net.*;

public class Accepter_connexion implements Runnable {

    private final ServerSocket socketserver;
    private Socket newClientSocket;

    public Thread receiver;
    private final AbstractController controller;


    
    public Accepter_connexion(ServerSocket serverSocket, AbstractController controller) {
        this.controller = controller;
        this.socketserver = serverSocket;

    }

    @Override
    public void run() {

        try {
            while (true) {

                newClientSocket = socketserver.accept();
                System.out.println("Un Client se connecte avec l'adresse : " + newClientSocket.getInetAddress().getHostAddress());

                receiver = new Thread(new PartieQuartoServeur(newClientSocket, controller));
                receiver.start();

            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }

    }
}
