/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.*;
import java.util.ArrayList;
import model.EntreeGUI;
import model.EtatGUI;
import model.Coord;
import model.NumeroJoueur;
import model.Partie;

/**
 *
 * @author Flo
 */
public class ControllerLocal extends AbstractController {

    public ControllerLocal(Partie partie) {
        super(partie);
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
            boolean quarto = partie.thereIsQuarto();
            if (quarto && partie.isValidationAutoEnabled()) {
                etatActuel = getJoueurCourant() == NumeroJoueur.J1 ? EtatGUI.J1ATrouveUnQuarto : EtatGUI.J2ATrouveUnQuarto;
                NotificationQuartoDetecte notifQuarto = new NotificationQuartoDetecte(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
                envoyerNotification(notifQuarto);
            }

            if (partie.isListPieceEmpty()) {
                etatActuel = partie.passerEtatSuivant(EntreeGUI.ListePieceVide);
                NotificationDernierTour notifQuarto = new NotificationDernierTour(getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());;
                envoyerNotification(notifQuarto);
            }
        }
        return rep;
    }
    
    public boolean notifierBotPremierTour(){
        if(partie.getJoueurCourant() == NumeroJoueur.J2){
                //bot.update(controllerLocal, null);
                //lancer le bot
                this.envoyerNotification(new NotificationPremierTour(NumeroJoueur.J2,partie.getEtatGUI(),null,getSortieGui()));
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

        NotificationQuartoAnnonce notif = new NotificationQuartoAnnonce(partie.getQuartos(), getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
        envoyerNotification(notif);
        return result;
    }

    @Override
    public boolean annoncerMatchNul() {
        if (partie.getEtatGUI() == EtatGUI.J2DernierTour || partie.getEtatGUI() == EtatGUI.J1DernierTour) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.J1AnnonceMatchNull : EntreeGUI.J2AnnonceMatchNull;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationMatchNullAnnonce notif = new NotificationMatchNullAnnonce(getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
            partie.changerJoueurCourant();
            envoyerNotification(notif);
            return true;
        }

        if (partie.getEtatGUI() == EtatGUI.J1PeutConfirmerMatchNull || partie.getEtatGUI() == EtatGUI.J2PeutConfirmerMatchNull) {
            EtatGUI etatprecedent = partie.getEtatGUI();
            EntreeGUI entree = getJoueurCourant() == NumeroJoueur.J1 ? EntreeGUI.J1AnnonceMatchNull : EntreeGUI.J2AnnonceMatchNull;
            EtatGUI etatActuel = partie.passerEtatSuivant(entree);
            NotificationMatchNullConfirme notif = new NotificationMatchNullConfirme(getJoueurCourant(), etatActuel, etatprecedent, getSortieGui());
            envoyerNotification(notif);
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
    
   

}
