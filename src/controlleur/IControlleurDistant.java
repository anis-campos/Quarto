/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlleur;

import Databse.Compte;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;
import model.SortieGUI;

/**
 * Identifie le joueur qui appel la méthode
 * @author Anis
 */
public interface IControlleurDistant extends Serializable{

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

        /**
     * Permet de recuperer le joueur courant
     *
     * @return Le numero de joueur
     */
    NumeroJoueur getJoueurCourant();

    /**
     * Permet de recuperer l'etat courant
     *
     * @return L'etat de la GUI
     */
    EtatGUI getEtatCourant();

    /**
     * Permet de recuperer l'etat de sortie de la GUI
     *
     * @return PartieEnCour ou PartieTerminee
     */
    SortieGUI getSortieGui();

    /**
     * Obtenir la liste des pièces disponibles
     *
     * @return Une liste de nom de pièces
     */
    List<Map.Entry<Integer, String>> getListPieceDisponible();

    /**
     * Obtenir la liste des pièces placées dans le plateau
     *
     * @return Une liste de nom de pièces
     */
    List<String> getListPiecePlacee();

    /**
     * Obtenir le nom d'un joeur
     * @param numeroJoueur numéro du joueur
     * @return Le nom du joueur
     */
    String getNomJoueur(NumeroJoueur numeroJoueur);

    /**
     * Etat de l'option validation automatique
     * @return 
     */
    Boolean getIsValidationAutoEnabled();

    /**
     * La liste des coordonnées des cases vides sur le plateau
     * @return 
     */
    public ArrayList<Coord> getAvailableCoords();
    
    /**
     * Permet de verifier que les joueurs passées en paramètres sont ceux de la partie
     * @param joueur1 Compte du joueur numéro 1
     * @param joeur2 Compte du joueur numéro 1
     * @return 
     */
    boolean VerifierJoueurs(Compte joueur1, Compte joeur2);
    
    public boolean onePlayer();
    
  
}
