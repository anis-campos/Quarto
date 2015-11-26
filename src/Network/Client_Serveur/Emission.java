package Network.Client_Serveur;

import controlleur.AbstractController;
import controlleur.observables.Notification;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EtatGUI;
import model.Joueur;
import model.NumeroJoueur;

public class Emission implements Runnable, Observer {

    private final ObjectOutputStream out;

    private final BlockingQueue<String> queue;
    private boolean continu;

    public Emission(ObjectOutputStream out, AbstractController controller) {
        this.out = out;
        this.queue = new ArrayBlockingQueue<>(10);
        controller.addObserver(this);
        this.continu = true;
    }

    @Override
    public void run() {

        while (continu) {
            try {
                String message = queue.take();
                System.out.println(message);
            } catch (InterruptedException ex) {
                Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        try {
            Notification notif = (Notification) arg;


            queue.put("Nouveau Message\n\t" + notif.toString());
            out.writeObject(notif);

        } catch (InterruptedException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
            continu = false;
        }
    }
}
