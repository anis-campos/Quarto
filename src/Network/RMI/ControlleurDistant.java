/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Databse.Compte;
import model.Coord;
import model.Partie;

/**
 *
 * @author Anis
 */
public class ControlleurDistant extends controlleur.ControllerLocal implements controlleur.IControlleurDistant {

    public ControlleurDistant(Partie partie) {
        super(partie);
    }

    @Override
    public boolean poserPiece(Compte joueur, Coord coord) {

        if (isGoodClient(joueur)) {

            return super.poserPiece(coord);
        }
        return false;
    }

    @Override
    public boolean donnerPieceAdversaire(Compte joueur) {
        if (isGoodClient(joueur)) {
            return super.donnerPieceAdversaire();
        }
        return false;
    }

    @Override
    public boolean selectionPiece(Compte joueur, int idPiece) {
        if (isGoodClient(joueur)) {
            return super.selectionPiece(idPiece);
        }
        return false;
    }

    @Override
    public boolean annoncerQuarto(Compte joueur) {
        if (isGoodClient(joueur)) {
            return super.annoncerQuarto();
        }
        return false;
    }

    @Override
    public boolean annoncerMatchNul(Compte joueur) {
        if (isGoodClient(joueur)) {
            return super.annoncerMatchNul();
        }
        return false;
    }

    @Override
    public boolean onePlayer() {
        return true;
    }

    private boolean isGoodClient(Compte joueur) {
        return getNomJoueur(getJoueurCourant()).equals(joueur.pseudo);
    }

}
