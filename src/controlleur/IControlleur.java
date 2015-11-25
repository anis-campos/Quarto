/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import java.util.ArrayList;
import model.EtatGUI;
import model.SortieGUI;
import java.util.List;
import java.util.Map;
import javafx.beans.Observable;
import model.Coord;
import model.NumeroJoueur;

/**
 *
 * @author Anis
 */
public interface IControlleur{

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
     * @param idPiece Nom de la pièce sélectionnée
     * @return True = la pièce a été placée dans la case du joueur courant
     */
    boolean selectionPiece(int idPiece);

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

    /**
     * Permet de recuperer le joueur courant
     * @return Le numero de joueur
     */
    NumeroJoueur getJoueurCourant();

    /**
     * Permet de recuperer l'etat courant
     * @return L'etat de la GUI
     */
    EtatGUI getEtatCourant();

    /**
     * Permet de recuperer l'etat de sortie de la GUI
     * @return PartieEnCour ou PartieTerminee
     */
    SortieGUI getSortieGui();

    /**
     * Obtenir la liste des pièces disponibles
     * @return Une liste de nom de pièces
     */
    List<Map.Entry<Integer,String>> getListPieceDisponible();

    /**
     * Obtenir la liste des pièces placées dans le plateau
     * @return Une liste de nom de pièces
     */
    List<String> getListPiecePlacee();
    
    String getNomJoueur(NumeroJoueur nj);

    Boolean getIsValidationAutoEnabled();

    public ArrayList<Coord> getAvailableCoords();
    
    boolean onePlayer();
  
}
