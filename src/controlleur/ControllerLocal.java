/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.Notification;
import controlleur.observables.NotificationQuartoDetecte;
import model.EntreeGUI;
import model.EtatGUI;
import model.SortieGUI;
import controlleur.observables.NotificationPieceDonnee;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import controlleur.observables.NotificationQuartoAnnoncer;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import model.Coord;
import model.NumeroJoueur;
import model.Partie;

/**
 *
 * @author Flo
 */
public class ControllerLocal extends Observable implements IControlleur {

    Partie partie;

    public ControllerLocal(Partie partie) {
        this.partie = partie;

    }

    @Override
    public boolean poserPiece(Coord coord) {
        boolean rep = partie.poserPiece(coord);
        if (rep) {

            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = EntreeGUI.Plateau;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationPiecePlacee notif = new NotificationPiecePlacee(coord, getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
            envoyerNotification(notif);

            //Verification des quarto
            boolean quarto = partie.thereIsQuarto(coord);
            if (quarto && partie.isValidationAutoEnabled()) {
                etatActuel = getJoueurCourant() == NumeroJoueur.J1 ? EtatGUI.J1ATrouveUnQuarto : EtatGUI.J2ATrouveUnQuarto;
                NotificationQuartoDetecte notifQuarto = new NotificationQuartoDetecte(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
                envoyerNotification(notifQuarto);

            }
        }
        return rep;
    }

    /**
     * Permet d'envoyer une notification.
     *
     * @param notif
     */
    private void envoyerNotification(Notification notif) {
        setChanged();
        notifyObservers(notif);

    }

    @Override
    public boolean donnerPieceAdversaire() {
        boolean rep = partie.donnerPieceAdversaire();

        if (rep) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.DonnerJ1 : EntreeGUI.DonnerJ2;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationPieceDonnee notif = new NotificationPieceDonnee(getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
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
            NotificationPieceSelectionnee notif = new NotificationPieceSelectionnee(idPiece, getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
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

        NotificationQuartoAnnoncer notif = new NotificationQuartoAnnoncer(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
        envoyerNotification(notif);
        return result;
    }

    @Override
    public boolean annoncerMatchNul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {

        return partie.getListPieceNameDisponibles();
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
    public boolean confirmerMatchNull() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        return partie.getNameJoueurFromNumero(nj);
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        return partie.isValidationAutoEnabled();
    }

}
