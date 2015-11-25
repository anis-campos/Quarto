/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anis
 */
public class Serveur implements Runnable {

    // <editor-fold defaultstate="collapsed" desc="Gestion du thread">
    private boolean isRunning;

    private static Serveur instance;

    private Serveur() {
        isRunning = true;
    }

    public static Serveur getInstance() {
        if (instance == null) {
            instance = new Serveur();
        }
        return instance;
    }

    public void stop() {
        isRunning = false;
    }
// </editor-fold>

    @Override
    public void run() {
        ServerSocket socketConnexion = null;
        Thread connexionThread;

        System.out.println("\t\t####################################");
        System.out.println("\t\t######### SERVEUR DE QUARTO ########");
        System.out.println("\t\t####################################");

        System.out.println("Description : Ce serveur permet de gerer plusieurs parties de quartos en réseau.");
        System.out.println("");
        System.out.println("///////////////////// DEMARRAGE /////////////////////  ");
        System.out.println("");

        System.out.print("[SERVEUR] - Création du socket de connexion...");

        try {
            socketConnexion = new ServerSocket(Constantes.portConnexion);
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("OK");

        System.out.print("[SERVEUR] - Création du sgestionnaire de connexion...");
        
        Accepter_clients accepter_clients = new Accepter_clients(socketConnexion);
        connexionThread = new Thread(accepter_clients);
        connexionThread.start();
        System.out.println("OK");

        while (isRunning) {

            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        try {
            accepter_clients.stop();
            socketConnexion.close();

        } catch (Exception ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    class Accepter_clients implements Runnable {

        private ServerSocket socketserver;
        private Socket socket;
        private int nbrclient = 1;
        private boolean isRunning;

        public Accepter_clients(ServerSocket s) {
            socketserver = s;
            isRunning = true;
        }

        public void stop() {
            isRunning = false;
        }

        @Override
        public void run() {

            try {
                while (isRunning) {
                    System.out.println("En Attente d'une connexion...");

                    socket = socketserver.accept(); // Un client se connecte on l'accepte
                    System.out.println("Le client numéro " + nbrclient + " est connecté !");
                    
                    InputStream inputStream = socket.getInputStream();
                    
                    byte[] b = new byte[10];
                    
                    inputStream.read(b, 0, 10);
                    
                    String toString = Arrays.toString(b);
                    
                    nbrclient++;
                    socket.close();
                }

            } catch (IOException ex) {
                System.err.println(ex);
            }
        }

    }

}
