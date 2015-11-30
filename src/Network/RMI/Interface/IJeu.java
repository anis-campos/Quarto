/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI.Interface;

import Databse.Compte;
import controlleur.IControlleur;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Coord;
import model.EtatGUI;
import model.NumeroJoueur;
import model.SortieGUI;

/**
 *
 * @author Anis
 */
public interface IJeu extends Remote, Serializable {

    /**
     * Pose une piece sur le plateau de jeu de la partie
     *
     * @param joueur
     * @param coord Coordonnées de la pièce à poser
     * @return True=La pièce s'est posé sans bug
     */
    boolean poserPiece(Coord coord) throws RemoteException;

    /**
     * Donne la pièce de la case du joueur courant à l'autre joueur
     *
     * @param joueur
     * @return True= La pièce a été transférée avec succès
     */
    boolean donnerPieceAdversaire() throws RemoteException;

    /**
     * Place la pièce sélectionnée dans la case du joueur courant
     *
     * @param joueur
     * @param idPiece Nom de la pièce sélectionnée
     * @return True = la pièce a été placée dans la case du joueur courant
     */
    boolean selectionPiece(int idPiece) throws RemoteException;

    /**
     * Vérifie que le quarto annoncé est valide
     *
     * @param joueur
     * @return True le quarto annoncé est valide
     */
    boolean annoncerQuarto() throws RemoteException;

    /**
     * Vérifie que le
     *
     * @param joueur
     * @return
     */
    boolean annoncerMatchNul() throws RemoteException;

    /**
     * Permet de recuperer le joueur courant
     *
     * @return Le numero de joueur
     */
    NumeroJoueur getJoueurCourant() throws RemoteException;

    /**
     * Permet de recuperer l'etat courant
     *
     * @return L'etat de la GUI
     */
    EtatGUI getEtatCourant() throws RemoteException;

    /**
     * Permet de recuperer l'etat de sortie de la GUI
     *
     * @return PartieEnCour ou PartieTerminee
     */
    SortieGUI getSortieGui() throws RemoteException;

    /**
     * Obtenir la liste des pièces disponibles
     *
     * @return Une liste de nom de pièces
     */
    List<Map.Entry<Integer, String>> getListPieceDisponible() throws RemoteException;

    /**
     * Obtenir la liste des pièces placées dans le plateau
     *
     * @return Une liste de nom de pièces
     */
    List<String> getListPiecePlacee() throws RemoteException;

    /**
     * Obtenir le nom d'un joeur
     *
     * @param numeroJoueur numéro du joueur
     * @return Le nom du joueur
     */
    String getNomJoueur(NumeroJoueur numeroJoueur) throws RemoteException;

    /**
     * Obtenir une description des parametres
     *
     * @return
     */
    String getParametres() throws RemoteException;

    /**
     * Etat de l'option validation automatique
     *
     * @return
     */
    Boolean getIsValidationAutoEnabled() throws RemoteException;

    /**
     * La liste des coordonnées des cases vides sur le plateau
     *
     * @return
     */
    ArrayList<Coord> getAvailableCoords() throws RemoteException;

    /**
     * Permet de verifier que les joueurs passées en paramètres sont ceux de la
     * partie
     *
     * @param joueur1 Compte du joueur numéro 1
     * @param joeur2 Compte du joueur numéro 1
     * @return
     */
    boolean VerifierJoueurs(Compte joueur1, Compte joeur2) throws RemoteException;

    boolean onePlayer() throws RemoteException;

    void registerClientCallback(IClientCallback client) throws RemoteException;

    void quiterPartie() throws RemoteException;
}
