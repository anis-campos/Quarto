/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import Network.RMI.ClientCallback;
import Databse.Compte;
import Network.RMI.Interface.*;
import static Network.RMI.Constantes.CONNEXION;
import Network.RMI.PartieItem;
import controlleur.observables.Notification;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author Anis
 */
public class Client {

    public static void main(String[] args) throws Exception {

        ILogin service = (ILogin) Naming.lookup("rmi:" + CONNEXION);

        

        ISession session = service.connexion("negga", "ytreza", new ClientCallback());

        System.out.println("Client Connecté !!!");

        List<Compte> comptes = session.listeComptes();

        for (Compte compte : comptes) {

            System.out.println(compte);
        }

        Compte compte = comptes.get(0);

        //List<PartieItem> listePartie = session.listePartie();
        final IJeu jeuJ1 = getOrCreatepartie(session, compte);

        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("Joueur 1 : " + jeuJ1.getNomJoueur(NumeroJoueur.J1));
                System.out.println("Joueur 2 : " + jeuJ1.getNomJoueur(NumeroJoueur.J2));
                System.out.println("Joueur Courant : " + jeuJ1.getJoueurCourant());
                /*
                 List<Map.Entry<Integer, String>> listPieceDisponible = PartiJeu.getListPieceDisponible();

                 for (Map.Entry<Integer, String> entry : listPieceDisponible) {
                 System.out.println(String.format("ID:%s - Piece: %s", entry.getKey(),entry.getValue()));
                 }
                 */
                System.out.println(" Selection de 1 : " + jeuJ1.selectionPiece(1));

                //PartiJeu.getJoueurCourant();
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        thread.start();
        thread.join();
    }

    public static IJeu getOrCreatepartie(ISession session, Compte compte) throws RemoteException {
        try {
            return session.creerPartieAvecAdversaire(
                    new Parametre(true, true, true, true, true, true, false, false),
                    compte);
        } catch (RemoteException ex) {

        }
        List<PartieItem> listePartie;
        listePartie = session.listePartie();
        return session.reprendrePartie(listePartie.get(0).ID);

    }



}
