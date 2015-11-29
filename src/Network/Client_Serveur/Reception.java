package Network.Client_Serveur;

import controlleur.observables.Notification;
import controlleur.observables.NotificationPieceDonnee;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.EtatGUI;

public class Reception implements Runnable {

    private final ControllerDistant controler;
    private final ObjectInputStream in;

    public Reception(ObjectInputStream in, ControllerDistant controller) {
        this.in = in;
        this.controler = controller;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Notification notif = (Notification) in.readObject();

                if(notif.nouvelEtat==EtatGUI.EtatNonDefinit) continue;
                
                if (notif instanceof NotificationPieceDonnee) {
                    NotificationPieceDonnee donner = (NotificationPieceDonnee) notif;
                     System.out.println("Nouvelles Action recu recu : Pièce donnée ");
                     
                    controler.donnerPieceAdversaire();
                } else if (notif instanceof NotificationPieceSelectionnee) {
                    NotificationPieceSelectionnee selectionner = (NotificationPieceSelectionnee) notif;
                    System.out.println("Nouvelles Action recu recu : Pièce selectionné " + selectionner.idPiece);
                    controler.selectionPiece(selectionner.idPiece);

                } else if (notif instanceof NotificationPiecePlacee) {
                    NotificationPiecePlacee placee = (NotificationPiecePlacee) notif;
                    System.out.println("Nouvelles Action recu recu : Pièce posée en " + placee.casePlateau);
                    controler.poserPiece(placee.casePlateau);
                }

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
                 }*/ {

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
