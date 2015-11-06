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

    /**
     * Pose une piece sur le plateau de jeu de la partie
     *
     * @param coord Coordonnées de la pièce à poser
     * @return True=La pièce s'est posé sans bug
     */
    boolean poserPiece(Coord coord);

    /**
     * Donne la pièce de la case du joueur courant à l'autre joueur
     *
     * @return True= La pièce a été transférée avec succès
     */
    boolean donnerPieceAdversaire();

    /**
     * Place la pièce sélectionnée dans la case du joueur courant
     *
     * @param nomPiece Nom de la pièce sélectionnée
     * @return True = la pièce a été placée dans la case du joueur courant
     */
    boolean selectionPiece(String nomPiece);

    /**
     * Vérifie que le quarto annoncé est valide
     *
     * @return True le quarto annoncé est valide
     */
    boolean annoncerQuarto();

    /**
     * Vérifie que le
     *
     * @return
     */
    boolean annoncerMatchNul();

    boolean confirmerMatchNull();

    NumeroJoueur getJoueurCourant();

    EtatGUI getEtatCourant();

    SortieGUI getSortieGui();

    List<String> getListPieceDisponible();

    List<String> getListPiecePlacee();

}
