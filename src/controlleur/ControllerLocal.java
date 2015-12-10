/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.Notification;
import controlleur.observables.NotificationDernierTour;
import controlleur.observables.NotificationMatchNullAnnonce;
import controlleur.observables.NotificationMatchNullConfirme;
import controlleur.observables.NotificationPieceDonnee;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import controlleur.observables.NotificationPremierTour;
import controlleur.observables.NotificationQuartoAnnonce;
import controlleur.observables.NotificationQuartoDetecte;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import model.Coord;
import model.EntreeGUI;
import model.EtatGUI;
import model.NumeroJoueur;
import model.Partie;
import model.SortieGUI;

/**
 *
 * @author Flo
 */
public class ControllerLocal extends Observable implements IControlleur {

    protected Partie partie;

    public ControllerLocal(Partie partie) {
        this.partie = partie;
    }

    @Override
    public NumeroJoueur getJoueurCourant() {
        return partie.getNumeroJoueurCourant();
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

    /**
     * Permet d'envoyer une notification.
     *
     * @param notif
     */
    protected void envoyerNotification(Notification notif) {
        setChanged();
        notifyObservers(notif);

    }

    @Override
    public boolean poserPiece(Coord coord) {
        boolean rep = partie.poserPiece(coord);
        if (rep) {

            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = EntreeGUI.Plateau;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationPiecePlacee notif = new NotificationPiecePlacee(coord, getJoueurCourant(), etatActuel, etatprecedent);
            envoyerNotification(notif);
            //Verification des quarto
            boolean quarto = partie.thereIsQuarto();
            if (quarto && partie.isValidationAutoEnabled()) {
                etatActuel = getJoueurCourant() == NumeroJoueur.J1 ? EtatGUI.J1ATrouveUnQuarto : EtatGUI.J2ATrouveUnQuarto;
                NotificationQuartoDetecte notifQuarto = new NotificationQuartoDetecte(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent);
                envoyerNotification(notifQuarto);
            }

            if (partie.isListPieceEmpty()) {
                etatActuel = partie.passerEtatSuivant(EntreeGUI.ListePieceVide);
                NotificationDernierTour notifQuarto = new NotificationDernierTour(getJoueurCourant(), etatActuel, etatprecedent);;
                envoyerNotification(notifQuarto);
            }
        }
        return rep;
    }

    public boolean notifierBotPremierTour() {
        if (partie.getNumeroJoueurCourant() == NumeroJoueur.J2) {
            //bot.update(controllerLocal, null);
            //lancer le bot
            this.envoyerNotification(new NotificationPremierTour(NumeroJoueur.J2, partie.getEtatGUI(), null));
            return true;
        }
        return false;
    }

    @Override
    public boolean donnerPieceAdversaire() {

        boolean rep = partie.donnerPieceAdversaire();

        if (rep) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.DonnerJ1 : EntreeGUI.DonnerJ2;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);

            NotificationPieceDonnee notif = new NotificationPieceDonnee(getJoueurCourant(), etatActuel, etatprecedent);
            partie.changerJoueurCourant();
            envoyerNotification(notif);
        }
        return rep;
    }

    @Override
    public boolean selectionPiece(int idPiece) {

        boolean rep = partie.selectionPiece(idPiece);
        if (rep) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = EntreeGUI.ListePiece;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationPieceSelectionnee notif = new NotificationPieceSelectionnee(idPiece, getJoueurCourant(), etatActuel, etatprecedent);
            envoyerNotification(notif);
        }

        return rep;
    }

    @Override
    public boolean annoncerQuarto() {
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
        envoyerNotification(notif);
        return result;
    }

    @Override
    public boolean annoncerMatchNul() {
        EtatGUI etatActuel = partie.getEtatGUI();
        EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.J1AnnonceMatchNull : EntreeGUI.J2AnnonceMatchNull;
        EtatGUI etatSuivant = partie.passerEtatSuivant(entree);
        Notification notif;

        if (etatActuel == EtatGUI.J2DernierTour || etatActuel == EtatGUI.J1DernierTour) {
            notif = new NotificationMatchNullAnnonce(getJoueurCourant(), etatSuivant, etatActuel);
            partie.changerJoueurCourant();
            envoyerNotification(notif);
            return true;
        }

        if (etatActuel == EtatGUI.J1PeutConfirmerMatchNull || etatActuel == EtatGUI.J2PeutConfirmerMatchNull) {
            notif = new NotificationMatchNullConfirme(getJoueurCourant(), etatSuivant, etatActuel);
            envoyerNotification(notif);
            return true;
        }

        return false;
    }

    @Override
    public ArrayList<Coord> getAvailableCoords() {
        return partie.getAvailableCoords();
    }

    @Override
    public boolean onePlayer() {
        return partie.onePlayer();
    }

    @Override
    public List<Map.Entry<Coord, String>> getListPiecePlateauJeu() {
        return  partie.getPiecesPlateauJeu();
    }

    @Override
    public String getNamePieceJ1() {
        return partie.getNamePieceJ1();
    }

    @Override
    public String getNamePieceJ2() {
        return partie.getNamePieceJ2();
    }

    @Override
    public int getBotLevel() {
        return partie.getBotLevel();
    }

}
