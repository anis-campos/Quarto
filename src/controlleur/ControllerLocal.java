/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.util.Observable;
import model.Coord;
import model.Joueur;
import model.Partie;
import model.Piece;


/**
 *
 * @author Flo
 */
public class ControllerLocal  extends Observable implements  IControlleur{

    Partie partie;

    
    public ControllerLocal(Partie partie) {
        this.partie=partie ;
    }

    
    
    @Override
    public boolean poserPiece(Piece piece, Coord coord) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean donnerPiece(Piece piece) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Joueur getJoueurCourant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
