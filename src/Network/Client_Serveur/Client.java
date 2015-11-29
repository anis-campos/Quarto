package Network.Client_Serveur;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import Network.Client_Serveur.Emission;
import Network.Client_Serveur.Reception;


public class Client implements Runnable {

    private final Socket socket;
    private final ControllerDistant controler;
 
    private Thread t4;
    private Thread t3;
    


    public Client(Socket socket, ControllerDistant controller) {
        this.socket = socket;
        this.controler = controller;
    }

    @Override
    public void run() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


                                    
            t4 = new Thread(new Emission(out,controler));
            t4.start();
            t3 = new Thread(new Reception(in,controler));
            t3.start();

        } catch (IOException e) {
            System.err.println("Le serveur distant s'est dconnect !");
        }

    }

}
