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

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
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

        ClientCallback clientCallbackJ1 = new ClientCallback("cJ1", NumeroJoueur.J1);
        ClientCallback clientCallbackJ2 = new ClientCallback("cJ2", NumeroJoueur.J2);

        ISession sessionJ1 = service.connexion("negga", "ytreza", clientCallbackJ1);

        ISession sessionJ2 = service.connexion("escroc", "azerty", clientCallbackJ2);

        System.out.println("Clients Connectés !!!");

        //List<PartieItem> listePartie = session.listePartie();
        final IJeu jeuJ1 = sessionJ1.creerPartieAvecAdversaire(
                new Parametre(true, true, true, true, true, true, false, false),
                sessionJ2.getCompteJoueurConnectee());
        jeuJ1.registerClientCallback(clientCallbackJ1);

        final List<PartieItem> listePartie = sessionJ2.listePartie();
        if (listePartie.size() != 1) {
            throw new Exception("Execution non conforme");
        }
        final IJeu jeuJ2 = sessionJ2.rejoindrePartie(listePartie.get(0).ID);
        jeuJ2.registerClientCallback(clientCallbackJ2);

        System.out.println("-------------- INFO PARTIE ----------------");
        System.out.println("\tJoueur 1 : " + jeuJ1.getNomJoueur(NumeroJoueur.J1));
        System.out.println("\tJoueur 2 : " + jeuJ1.getNomJoueur(NumeroJoueur.J2));
        System.out.println("\tJoueur Courant : " + jeuJ1.getJoueurCourant());
        System.out.println("\tParametres : " + listePartie.get(0).Parametres);
        System.out.println("\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        final Semaphore sem = new Semaphore(1);

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {

                    sem.acquire();

                    System.out.println(" [J1] - Selection de 1 : " + jeuJ1.selectionPiece(1));
                    System.out.println(" [J1] - Selection de 2 : " + jeuJ1.selectionPiece(2));
                    System.out.println(" [J1] - Selection de 3 : " + jeuJ1.selectionPiece(3));
                    System.out.println(" [J1] - Selection de 1 : " + jeuJ1.selectionPiece(1));
                    System.out.println(" [J1] - donner pièce 1 : " + jeuJ1.donnerPieceAdversaire());

                    sem.release();

                } catch (RemoteException | InterruptedException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        executor.execute(new Runnable() {

            @Override
            public void run() {

                try {
                    sem.acquire();
                    System.out.println(" [J2] - Pose de 1 en (3,3) : " + jeuJ2.poserPiece(new Coord(3, 3)));
                    System.out.println(" [J2] - Selection de 2 : " + jeuJ2.selectionPiece(2));
                    System.out.println(" [J1] - donner pièce 2 : " + jeuJ2.donnerPieceAdversaire());
                } catch (InterruptedException | RemoteException ex) {
                    Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        executor.shutdown();

        executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);

        jeuJ1.quiterPartie();

        jeuJ2.quiterPartie();

        sessionJ1.logout();

        sessionJ2.logout();

        System.exit(0);

    }

}
