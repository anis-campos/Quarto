package Network.socket.Common;


import controlleur.AbstractController;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reception implements Runnable {

    private final AbstractController controler;
    private final ObjectInputStream in;

    public Reception(ObjectInputStream in, AbstractController controller) {
        this.in = in;
        this.controler =  controller;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Object notif = in.readObject();

                /*if (notif instanceof MoveNotification) {
                    MoveNotification mn = (MoveNotification) notif;
                    System.out.println("Nouvelles Action recu recu : Move de " + mn.Init + " à " + mn.Final);
                    this.controller.moveRemote(mn.Init, mn.Final);
                } else if (notif instanceof PromotedNotification) {
                    PromotedNotification pn = (PromotedNotification) notif;
                     System.out.println("Nouvelles Action recu recu : Promote de " + pn.CoordPion + " en " + pn.newType);
                    this.controller.promote(pn.CoordPion, pn.newType);
                } else {
                    System.out.println("Nouvelles Action recu recu : Non Reconnu");
                }*/


            } catch (IOException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
