/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import Databse.Compte;
import Network.RMI.Interface.IClientCallback;
import controlleur.observables.*;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import model.*;
import org.apache.log4j.Logger;

/**
 *
 * @author Anis
 */
public class ControlleurDistant implements IControlleurDistant {

    private static final Logger logger = Logger.getLogger(ControlleurDistant.class);
    Partie partie;


    static final int N_J1 = 1;
    static final int N_J2 = 2;
    static final int N_Spectateur = 4;

    IClientCallback cJ1, cJ2;

    List<IClientCallback> cSpectateurs;

    private void envoyerNotification(int id, Notification notif) {
        ArrayList<Integer> destinataires = new ArrayList<>(3);
        switch (id) {
            case 1:
            case 2:
            case 4:
                destinataires.add(id);
                break;

            case 3:
                destinataires.add(1);
                destinataires.add(2);
                break;

            case 5:
                destinataires.add(1);
                destinataires.add(4);
                break;

            case 6:
                destinataires.add(2);
                destinataires.add(4);
                break;

            case 7:
                destinataires.add(1);
                destinataires.add(2);
                destinataires.add(4);
                break;
        }
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (Integer destinataire : destinataires) {
            switch (destinataire) {
                case N_J1:
                    executor.execute(new threadNotif(cJ1, notif));

                    break;
                case N_J2:
                    executor.execute(new threadNotif(cJ2, notif));

                    break;
                case N_Spectateur:
                    for (IClientCallback cSpectateur : cSpectateurs) {
                        executor.execute(new threadNotif(cSpectateur, notif));
                    }
                    break;

            }
        }
        executor.shutdown();

        try {
            executor.awaitTermination(20, TimeUnit.SECONDS);

            //notifieurs.get(destinataire).envoyerNotification(notif);
        } catch (InterruptedException ex) {
            logger.error(ex);
        }
    }

    public ControlleurDistant(Partie partie) {
        this.partie = partie;


        cSpectateurs = new ArrayList<>();

    }

    @Override
    public boolean poserPiece(Compte joueur, Coord coord) {

        if (isGoodClient(joueur)) {

            boolean rep = partie.poserPiece(coord);
            if (rep) {

                EtatGUI etatprecedent = partie.getEtatGUI();
                EntreeGUI entree = EntreeGUI.Plateau;
                EtatGUI etatActuel = partie.passerEtatSuivant(entree);
                NotificationPiecePlacee notif = new NotificationPiecePlacee(coord, getJoueurCourant(), etatActuel, etatprecedent);
                envoyerNotification(N_J1 | N_J2 | N_Spectateur, notif);
                //Verification des quarto
                boolean quarto = partie.thereIsQuarto();
                if (quarto && partie.isValidationAutoEnabled()) {
                    etatActuel = getJoueurCourant() == NumeroJoueur.J1 ? EtatGUI.J1ATrouveUnQuarto : EtatGUI.J2ATrouveUnQuarto;
                    NotificationQuartoDetecte notifQuarto = new NotificationQuartoDetecte(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent);
                    envoyerNotification(N_J1 | N_J2 | N_Spectateur, notifQuarto);
                }

                if (partie.isListPieceEmpty()) {
                    etatActuel = partie.passerEtatSuivant(EntreeGUI.ListePieceVide);
                    NotificationDernierTour dernierTour = new NotificationDernierTour(getJoueurCourant(), etatActuel, etatprecedent);
                    int destinataire = getJoueurCourant() == NumeroJoueur.J1 ? N_J2 : N_J1;
                    envoyerNotification(destinataire, dernierTour);
                }
            }
            return rep;
        }
        return false;
    }

    @Override
    public boolean donnerPieceAdversaire(Compte joueur) {
        if (isGoodClient(joueur)) {
            boolean rep = partie.donnerPieceAdversaire();

            if (rep) {
                EtatGUI etatprecedent = partie.getEtatGUI();
                EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.DonnerJ1 : EntreeGUI.DonnerJ2;
                EtatGUI etatActuel = partie.passerEtatSuivant(entree);

                NotificationPieceDonnee notif = new NotificationPieceDonnee(getJoueurCourant(), etatActuel, etatprecedent);
                partie.changerJoueurCourant();
                envoyerNotification(N_J1 | N_J2 | N_Spectateur, notif);
            }
            return rep;
        }
        return false;
    }

    @Override
    public boolean selectionPiece(Compte joueur, int idPiece) {
        if (isGoodClient(joueur)) {

            boolean rep = partie.selectionPiece(idPiece);
            if (rep) {
                EtatGUI etatprecedent = partie.getEtatGUI();
                EntreeGUI entree = EntreeGUI.ListePiece;
                EtatGUI etatActuel = partie.passerEtatSuivant(entree);
                NotificationPieceSelectionnee notif = new NotificationPieceSelectionnee(idPiece, getJoueurCourant(), etatActuel, etatprecedent);
                envoyerNotification(N_J1 | N_J2 | N_Spectateur, notif);
            }
        }
        return false;
    }

    @Override
    public boolean annoncerQuarto(Compte joueur) {
        if (isGoodClient(joueur)) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.J1AnnonceQuarto : EntreeGUI.J2AnnonceQuarto;
            //Mode un joueur à annoncé quarto
            partie.passerEtatSuivant(entree);
            EtatGUI etatActuel;
            //Veification du quarto
            boolean result = partie.annoncerQuarto();
            if (result) {
                etatActuel = partie.passerEtatSuivant(EntreeGUI.Quarto);
            } else {
                etatActuel = partie.passerEtatSuivant(EntreeGUI.PasQuarto);
            }

            NotificationQuartoAnnonce notif = new NotificationQuartoAnnonce(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent);
            envoyerNotification(N_J1 | N_J2 | N_Spectateur, notif);
            return result;
        }
        return false;
    }

    @Override
    public boolean annoncerMatchNul(Compte joueur) {
        if (isGoodClient(joueur)) {
            EtatGUI etatActuel = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.J1AnnonceMatchNull : EntreeGUI.J2AnnonceMatchNull;
            EtatGUI etatSuivant = partie.passerEtatSuivant(entree);
            Notification notif;

            if (etatActuel == EtatGUI.J2DernierTour || etatActuel == EtatGUI.J1DernierTour) {
                notif = new NotificationMatchNullAnnonce(getJoueurCourant(), etatSuivant, etatActuel);
                partie.changerJoueurCourant();
                int destinataire = getJoueurCourant() == NumeroJoueur.J1 ? N_J2 : N_J1;
                envoyerNotification(destinataire, notif);
                return true;
            }

            if (etatActuel == EtatGUI.J1PeutConfirmerMatchNull || etatActuel == EtatGUI.J2PeutConfirmerMatchNull) {
                notif = new NotificationMatchNullConfirme(getJoueurCourant(), etatSuivant, etatActuel);
                envoyerNotification(N_J1 | N_J2 | N_Spectateur, notif);
                return true;
            }

            return false;
        }
        return false;
    }

    @Override
    public boolean VerifierJoueurs(Compte joueur1, Compte joueur2) {
        return getNomJoueur(NumeroJoueur.J1).equals(joueur1.pseudo) && getNomJoueur(NumeroJoueur.J2).equals(joueur2.pseudo);
    }

    private boolean isGoodClient(Compte joueur) {
        logger.info(String.format("Joueur Courant: %s, Pseudo: %s", getNomJoueur(getJoueurCourant()), joueur.pseudo));
        return getNomJoueur(getJoueurCourant()).equals(joueur.pseudo);
    }

    @Override
    public NumeroJoueur getJoueurCourant() {
        return partie.getNumeroJoueurCourant();
    }

    @Override
    public List<String> getListPiecePlacee() {
        return partie.getListPieceNamePlacees();
    }

    @Override
    public EtatGUI getEtatCourant() {
        return partie.getEtatGUI();
    }

    @Override
    public SortieGUI getSortieGui() {
        return partie.getSortieGUI();
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        return partie.getNameJoueurFromNumero(nj);
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        return partie.isValidationAutoEnabled();
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {

        return partie.getListPieceNameDisponibles();
    }

    @Override
    public ArrayList<Coord> getAvailableCoords() {
        return partie.getAvailableCoords();
    }

    public void addObserver(IClientCallback client, Compte joueur) {
        if (getNomJoueur(NumeroJoueur.J1).equals(joueur.pseudo)) {
            cJ1 = client;
        } else if (getNomJoueur(NumeroJoueur.J2).equals(joueur.pseudo)) {
            cJ2 = client;
        } else {
            cSpectateurs.add(client);
        }

    }

    @Override
    public boolean onePlayer() {
        return false;
    }

    class threadNotif implements Serializable, Runnable {

        private final IClientCallback callback;

        private final Notification notification;

        public threadNotif(IClientCallback callback, Notification notification) {
            this.callback = callback;
            this.notification = notification;
        }

        @Override
        public void run() {
            if (callback != null && notification != null) {
                try {
                    callback.notifyMe(notification);
                } catch (RemoteException ex) {
                    logger.error(ex);
                }
            }

        }

    }

    class Notifieur extends Observable implements Serializable {

        public void envoyerNotification(final Notification notif) {
            setChanged();
            Thread thread = new Thread(new Runnable() {

                @Override
                public void run() {
                    notifyObservers(notif);
                }
            });
            thread.start();
        }

    }

}
