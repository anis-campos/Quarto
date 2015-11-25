package Network.socket.server;

import Network.socket.Common.Emission;
import controlleur.AbstractController;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import Network.socket.Common.Reception;

public class PartieQuartoServeur implements Runnable {

    private Socket clientSocket = null;
    private ObjectInputStream in = null;
    private ObjectOutputStream out = null;
    private Thread t3, t4;
    private final AbstractController controller;
    private final InetAddress ClientIP;

    PartieQuartoServeur(Socket lastSocket, AbstractController controller) {
        this.clientSocket = lastSocket;
        this.controller = controller;
        this.ClientIP = lastSocket.getInetAddress();

    }

    @Override
    public void run() {

        try {
            in = new ObjectInputStream(clientSocket.getInputStream());
            out = new ObjectOutputStream(clientSocket.getOutputStream());

            t3 = new Thread(new Reception(in, controller));
            t3.start();
            t4 = new Thread(new Emission(out, controller));
            t4.start();

        } catch (Exception e) {
            System.err.println("Connection closed with the client that has IP Address: " + ClientIP);
        }
    }
}
