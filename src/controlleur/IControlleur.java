/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import model.EtatGUI;
import model.SortieGUI;
import java.util.List;
import model.Coord;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public interface IControlleur {
     
    boolean poserPiece( Coord coord);
    boolean donnerPieceAdversaire();

    boolean selectionPiece(String nomPiece);

    boolean annoncerQuarto();

    boolean annoncerMatchNul();

    NumeroJoueur getJoueurCourant();
    
    EtatGUI getEtatCourant();
    
    SortieGUI getSortieGui();

    List<String> getListPieceDisponible();

    List<String> getListPiecePlacee();
    
    String getNomJoueur(NumeroJoueur nj);

}
