/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.NotificationPieceDonnee;
import controlleur.observables.NotificationPiecePlacee;
import controlleur.observables.NotificationPieceSelectionnee;
import java.util.List;
import java.util.Observable;
import model.Coord;
import model.Joueur;
import model.NumeroJoueur;
import model.Partie;
import model.Piece;
import view.EntreeGUI;
import view.EtatGUI;
import view.MatriceDeTransition;

/**
 *
 * @author Flo
 */
public class ControllerLocal extends Observable implements IControlleur {

    Partie partie;
    EtatGUI etatActuel;

    public ControllerLocal(Partie partie) {
        this.partie = partie;
        this.etatActuel = EtatGUI.J1DoitChoisir;
    }

    @Override
    public boolean poserPiece(Coord coord) {
        boolean rep = partie.poserPiece(coord);
        if (rep) {
            EtatGUI etatprecedent = etatActuel;
            EntreeGUI entree = EntreeGUI.Plateau;
            etatActuel = MatriceDeTransition.getInstance().getEtatSuivant(etatActuel, entree);
            NotificationPiecePlacee notif = new NotificationPiecePlacee(coord, getJoueurCourant(), etatActuel, etatprecedent);
            setChanged();
            notifyObservers(notif);
        }
        return rep;
    }

    @Override
    public boolean donnerPieceAdversaire() {
        boolean rep = partie.donnerPieceAdversaire();

        if (rep) {
            EtatGUI etatprecedent = etatActuel;
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.DonnerJ1 : EntreeGUI.DonnerJ2;
            etatActuel = MatriceDeTransition.getInstance().getEtatSuivant(etatActuel, entree);
            NotificationPieceDonnee notif = new NotificationPieceDonnee(getJoueurCourant(), etatActuel, etatprecedent);
            partie.changerJoueurCourant();
            setChanged();
            notifyObservers(notif);
        }
        return rep;
    }

    @Override
    public boolean selectionPiece(String nomPiece) {

        boolean rep = partie.selectionPiece(nomPiece);
        if (rep) {
            EtatGUI etatprecedent = etatActuel;

            etatActuel = MatriceDeTransition.getInstance().getEtatSuivant(etatActuel, EntreeGUI.ListePiece);
            NotificationPieceSelectionnee notif = new NotificationPieceSelectionnee(nomPiece, getJoueurCourant(), etatActuel, etatprecedent);
            setChanged();
            notifyObservers(notif);
        }

        return rep;
    }

    @Override
    public boolean annoncerQuarto() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean annoncerMatchNul() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getListPieceDisponible() {

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

}
