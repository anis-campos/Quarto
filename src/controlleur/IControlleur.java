/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import model.Coord;
import model.Joueur;
import model.Piece;

/**
 *
 * @author Anis
 */
public interface IControlleur {
     
    boolean poserPiece(Piece piece, Coord coord);
    boolean donnerPiece(Piece piece);
    boolean annoncerQuarto();
    boolean annoncerMatchNul();
    
    Joueur getJoueurCourant();
    
    
}
