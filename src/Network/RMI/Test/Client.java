/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Test;

import Network.RMI.ClientCallback;
import static Network.RMI.Constantes.PORT_RMI_CLIENT_CALLBACK;
import static Network.RMI.Constantes.URL_CONNEXION;
import Network.RMI.Interface.IJeu;
import Network.RMI.Interface.ILogin;
import Network.RMI.Interface.ISession;
import Network.RMI.MyRMISocketFactory;
import Network.RMI.PartieItem;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.RMISocketFactory;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Coord;
import model.NumeroJoueur;
import model.Parametre;

/**
 *
 * @author Anis
 */
public class Client {

    private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(Client.class);

    public static void main(String[] args) throws Exception {

        
        //String URL_CONNEXION = "rmi://" + ":" + PORT_RMI + CONNEXION;
        logger.info("L'URL paramétré est : \n\t" + URL_CONNEXION);

        ILogin service = (ILogin) Naming.lookup(URL_CONNEXION);

        ISession sessionJ1 = service.connexion("negga", "ytreza");

        ISession sessionJ2 = service.connexion("escroc", "azerty");

        logger.info("J1 : \n\t" + sessionJ1);
        logger.info("J2 : \n\t" + sessionJ2);

        logger.info("Clients Connectés !!!");

        System.setProperty("java.rmi.server.hostname", "127.0.0.1");
        RMISocketFactory.setSocketFactory(new MyRMISocketFactory(PORT_RMI_CLIENT_CALLBACK));
        ClientCallback clientCallbackJ1 = new ClientCallback();
        ClientCallback clientCallbackJ2 = new ClientCallback();

        logger.info("Callback J1 : \n\t" + clientCallbackJ1);
        logger.info("Callback J2 : \n\t" + clientCallbackJ2);

        logger.info("LOGIN :\n\t" + service);

        //List<PartieItem> listePartie = session.listePartie();
        final IJeu jeuJ1 = sessionJ1.creerPartieAvecAdversaire(
                new Parametre(true, true, true, true, true, true, false, false, false),
                sessionJ2.getCompteJoueurConnectee());
        logger.info("PARTIE CREE!!!\n");
        logger.info("Interface J1\n\t" + jeuJ1);

        jeuJ1.registerClientCallback(clientCallbackJ1);

        logger.info("CLIENT J1 ENREGISTRE!!!\n");

        final List<PartieItem> listePartie = sessionJ2.listePartie();
        if (listePartie.size() != 1) {
            throw new Exception("Execution non conforme");
        }
        logger.info("LIST DES PARTIES RECUPEREE!!!\n");

        final IJeu jeuJ2 = sessionJ2.rejoindrePartie(listePartie.get(0).ID);
        logger.info("J2 A RECUPERER SON INTERFACE DE JEU!!!\n");
        logger.info("Interface J2\n\t" + jeuJ2);

//        jeuJ2.registerClientCallback(clientCallbackJ2);
        logger.info("CLIENT J2 ENREGISTRE!!!\n");

        logger.info("-------------- INFO PARTIE ----------------");
        logger.info("\tJoueur 1 : " + jeuJ1.getNomJoueur(NumeroJoueur.J1));
        logger.info("\tJoueur 2 : " + jeuJ1.getNomJoueur(NumeroJoueur.J2));
        logger.info("\tJoueur Courant : " + jeuJ1.getJoueurCourant());
        logger.info("\tParametres : " + listePartie.get(0).Parametres);
        logger.info("\n");

        ExecutorService executor = Executors.newFixedThreadPool(2);

        final Semaphore sem = new Semaphore(1);

        executor.execute(new Runnable() {

            @Override
            public void run() {
                try {

                    sem.acquire();

                    logger.info(" [J1] - Selection de 1 : " + jeuJ1.selectionPiece(1));
                    logger.info(" [J1] - Selection de 2 : " + jeuJ1.selectionPiece(2));
                    logger.info(" [J1] - Selection de 3 : " + jeuJ1.selectionPiece(3));
                    logger.info(" [J1] - Selection de 1 : " + jeuJ1.selectionPiece(1));
                    logger.info(" [J1] - donner pièce 1 : " + jeuJ1.donnerPieceAdversaire());

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
                    logger.info(" [J2] - Pose de 1 en (3,3) : " + jeuJ2.poserPiece(new Coord(3, 3)));
                    logger.info(" [J2] - Selection de 2 : " + jeuJ2.selectionPiece(2));
                    logger.info(" [J1] - donner pièce 2 : " + jeuJ2.donnerPieceAdversaire());
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
