package socket.Common;

import controller.ChessGameControlers;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Emission implements Runnable, Observer {

    private final ObjectOutputStream out;

    private final BlockingQueue<String> queue;
    private boolean continu;

    public Emission(ObjectOutputStream out, ChessGameControlers controller) {
        this.out = out;
        this.queue = new ArrayBlockingQueue<>(10);
        controller.addObserver((Observer) this);
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
            queue.put("Nouveau Message");
            out.writeObject(arg);

        } catch (InterruptedException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Emission.class.getName()).log(Level.SEVERE, null, ex);
            continu = false;
        }
    }
}
