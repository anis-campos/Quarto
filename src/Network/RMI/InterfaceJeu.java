/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import Databse.Compte;
import Network.RMI.Interface.IClientCallback;
import Network.RMI.Interface.IJeu;
import controlleur.IControlleurDistant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.*;

public class InterfaceJeu implements IJeu {

    IClientCallback client;

    Compte Joueur;

    IControlleurDistant controleurPartieServeur;

    public InterfaceJeu(IClientCallback client, Compte Joeur, IControlleurDistant controleur) {
        this.client = client;
        this.Joueur = Joeur;
        this.controleurPartieServeur = controleur;
    }

    @Override
    public boolean poserPiece(Coord coord) {
        return controleurPartieServeur.poserPiece(Joueur, coord);
    }

    @Override
    public boolean donnerPieceAdversaire() {
        return controleurPartieServeur.donnerPieceAdversaire(Joueur);
    }

    @Override
    public boolean selectionPiece(int idPiece) {
        return controleurPartieServeur.selectionPiece(Joueur,idPiece);
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
    public NumeroJoueur getJoueurCourant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EtatGUI getEtatCourant() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SortieGUI getSortieGui() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Map.Entry<Integer, String>> getListPieceDisponible() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> getListPiecePlacee() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getNomJoueur(NumeroJoueur nj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Boolean getIsValidationAutoEnabled() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Coord> getAvailableCoords() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean onePlayer() {
        return false;
    }

}
