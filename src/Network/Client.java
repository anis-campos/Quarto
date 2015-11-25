package Network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Client {

    public static void main(String[] args) {

        if(args.length!=2)
        {
            System.err.println("Veuillez entrer le numero client !!!");
            return;
        }
        
        String idClient = args[1];
        
        Socket socket;
        try {
            socket = new Socket("localhost", Constantes.portConnexion);
            OutputStream outputStream = socket.getOutputStream();
            
            outputStream.write(idClient.getBytes(StandardCharsets.UTF_8));
            
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(Serveur.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
