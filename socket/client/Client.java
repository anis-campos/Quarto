package socket.client;

import controller.ChessGameControlers;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import socket.Common.Emission;
import socket.Common.Reception;

public class Client implements Runnable {

    private final Socket socket;
    private final ChessGameControlers chessGameControler;
 
    private Thread t4;
    private Thread t3;
    


    public Client(Socket socket, ChessGameControlers chessGameControler) {
        this.socket = socket;
        this.chessGameControler = chessGameControler;
    }

    @Override
    public void run() {

        try {
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());


                                    
            t4 = new Thread(new Emission(out,chessGameControler));
            t4.start();
            t3 = new Thread(new Reception(in,chessGameControler));
            t3.start();

        } catch (IOException e) {
            System.err.println("Le serveur distant s'est dconnect !");
        }

    }

}
