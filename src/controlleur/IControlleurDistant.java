/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import Databse.Compte;
import java.util.ArrayList;
import model.EtatGUI;
import model.SortieGUI;
import java.util.List;
import java.util.Map;
import model.Coord;
import model.NumeroJoueur;

/**
 * Identifie le joueur qui appel la méthode
 * @author Anis
 */
public interface IControlleurDistant{

    /**
     * Pose une piece sur le plateau de jeu de la partie
     *
     * @param joueur
     * @param coord Coordonnées de la pièce à poser
     * @return True=La pièce s'est posé sans bug
     */
    boolean poserPiece(Compte joueur,Coord coord);

    /**
     * Donne la pièce de la case du joueur courant à l'autre joueur
     *
     * @param joueur
     * @return True= La pièce a été transférée avec succès
     */
    boolean donnerPieceAdversaire(Compte joueur);

    /**
     * Place la pièce sélectionnée dans la case du joueur courant
     *
     * @param joueur
     * @param idPiece Nom de la pièce sélectionnée
     * @return True = la pièce a été placée dans la case du joueur courant
     */
    boolean selectionPiece(Compte joueur,int idPiece);

    /**
     * Vérifie que le quarto annoncé est valide
     *
     * @param joueur
     * @return True le quarto annoncé est valide
     */
    boolean annoncerQuarto(Compte joueur);

    /**
     * Vérifie que le
     *
     * @param joueur
     * @return
     */
    boolean annoncerMatchNul(Compte joueur);

    
  
}
