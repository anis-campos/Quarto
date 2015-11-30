/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import controlleur.ControlleurDistant;
import Databse.Compte;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.*;

public class InterfaceJeu extends UnicastRemoteObject implements IJeu {

    Compte Joueur;

    ControlleurDistant controleurPartieServeur;

    public InterfaceJeu(Compte joueur, ControlleurDistant controleur) throws RemoteException
   {

        this.Joueur = joueur;
        controleurPartieServeur = controleur;
        
    }

    @Override
    public boolean poserPiece(Coord coord) {
        return controleurPartieServeur.poserPiece(Joueur,coord);
    }

    @Override
    public boolean donnerPieceAdversaire() {
        return controleurPartieServeur.donnerPieceAdversaire(Joueur);
    }

    @Override
    public boolean selectionPiece(int idPiece) {
        return controleurPartieServeur.selectionPiece(Joueur, idPiece);
    }

    @Override
    public boolean annoncerQuarto() {
        return controleurPartieServeur.annoncerMatchNul(Joueur);
    }

    @Override
    public boolean annoncerMatchNul() {
        return controleurPartieServeur.annoncerMatchNul(Joueur);
    }

    @Override
    public NumeroJoueur getJoueurCourant() {
        return controleurPartieServeur.getJoueurCourant();
    }

    @Override
    public EtatGUI getEtatCourant() {
        return controleurPartieServeur.getEtatCourant();
    }

    @Override
    public SortieGUI getSortieGui() {
        return controleurPartieServeur.getSortieGui();
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {
        return controleurPartieServeur.getListPieceDisponible();
    }

    @Override
    public List<String> getListPiecePlacee() {
        return controleurPartieServeur.getListPiecePlacee();
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        return controleurPartieServeur.getNomJoueur(nj);
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        return controleurPartieServeur.getIsValidationAutoEnabled();
    }

    @Override
    public ArrayList<Coord> getAvailableCoords() {
        return controleurPartieServeur.getAvailableCoords();
    }

    @Override
    public boolean onePlayer() {
        return controleurPartieServeur.onePlayer();
    }

    @Override
    public void registerClientCallback(IClientCallback client) throws RemoteException {
        controleurPartieServeur.addObserver(client, Joueur);
    }

}
