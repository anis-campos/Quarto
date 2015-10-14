package  socket.server;


import controller.ChessGameControlers;
import java.io.*;
import java.net.*;

public class Accepter_connexion implements Runnable {

    private final ServerSocket socketserver;
    private Socket lastClientSocket;

    public Thread receiver;
    private final ChessGameControlers controller;


    
    public Accepter_connexion(ServerSocket serverSocket, ChessGameControlers controller) {
        this.controller = controller;
        this.socketserver = serverSocket;

    }

    @Override
    public void run() {

        try {
            while (true) {

                lastClientSocket = socketserver.accept();
                System.out.println("Un Client se connecte avec l'adresse : " + lastClientSocket.getInetAddress().getHostAddress());

                receiver = new Thread(new ChessGameServeur(lastClientSocket, controller));
                receiver.start();

            }
        } catch (IOException e) {

            System.err.println("Erreur serveur");
        }

    }
}
