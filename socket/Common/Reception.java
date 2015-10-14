package socket.Common;

import controller.*;
import controller.controllerRemote.ChessGameControler;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reception implements Runnable {

    private final ChessGameControler chessGameControler;
    private final ObjectInputStream in;

    public Reception(ObjectInputStream in, ChessGameControlers chessGameControler) {
        this.in = in;
        this.chessGameControler = (ChessGameControler) chessGameControler;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Object notif = in.readObject();

                if (notif instanceof MoveNotification) {
                    MoveNotification mn = (MoveNotification) notif;
                    System.out.println("Nouvelles Action recu recu : Move de " + mn.Init + " à " + mn.Final);
                    this.chessGameControler.moveRemote(mn.Init, mn.Final);
                } else if (notif instanceof PromotedNotification) {
                    PromotedNotification pn = (PromotedNotification) notif;
                     System.out.println("Nouvelles Action recu recu : Promote de " + pn.CoordPion + " en " + pn.newType);
                    this.chessGameControler.promote(pn.CoordPion, pn.newType);
                } else {
                    System.out.println("Nouvelles Action recu recu : Non Reconnu");
                }


            } catch (IOException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
