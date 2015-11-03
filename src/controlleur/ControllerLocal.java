/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import controlleur.observables.PieceDonneeNotification;
import java.util.List;
import java.util.Observable;
import model.Coord;
import model.NumeroJoueur;
import model.Partie;
import model.Piece;

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
    public boolean poserPiece(String nomPiece, Coord coord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean donnerPiece(String nomPiece) {
        Piece piece;
        piece = partie.findPieceAvailable(nomPiece);
        if (piece != null) {
            partie.donnerPiece(piece);
            setChanged();
            notifyObservers(new  PieceDonneeNotification(nomPiece, null, partie.getJoueurCourant()));
            partie.changerJoueurCourant();
        } else {
            return false;

        }
        return true;
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

}
