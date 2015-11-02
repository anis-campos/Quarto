/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.util.List;
import model.Coord;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public interface IControlleur {
     
    boolean poserPiece(String nomPiece, Coord coord);
    boolean donnerPiece(String nomPiece);
    boolean annoncerQuarto();
    boolean annoncerMatchNul();
    
    NumeroJoueur getJoueurCourant();
    
    List<String> getListPieceDisponible();
    
    
}
