/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IA;

import java.util.ArrayList;
import model.Coord;
import model.Parametre;
import model.Piece;
import model.PlateauJeu;
import model.QuartoCalculator;

/**
 *
 * @author timotheetroncy
 */
public class NodeData implements Comparable{
    private final PlateauJeu plateauJeu;
    private final ArrayList<Piece> listPiece;
    private final int depth;
    private final Coord coordDernierePiecePosee;
    private final Piece dernierePiecePosee;
    private int gain;
    private final Boolean adversaire;

    public NodeData(PlateauJeu plateauJeu, ArrayList<Piece> listPiece, Parametre p, int depth, Boolean adversaire, Coord coordDernierePiecePosee, Piece dernierePiecePosee) {
        this.plateauJeu = plateauJeu;
        this.listPiece = listPiece;
        this.depth = depth;
        this.adversaire = adversaire;
        this.dernierePiecePosee = dernierePiecePosee;
        this.coordDernierePiecePosee = coordDernierePiecePosee;
        evalNode(p);
    }
    
    private void evalNode(Parametre p){
        if (QuartoCalculator.thereIsQuarto(plateauJeu, p, coordDernierePiecePosee)) {
            if(adversaire){
                gain = -100*(depth+5);
            }else{
                gain = 100*(depth+5);
            }
        }else{
            gain = 0;
        }
    }
    
    public int getGain(){
        return gain;
    }

    public PlateauJeu getPlateauJeu() {
        return plateauJeu;
    }

    public ArrayList<Piece> getListPiece() {
        return listPiece;
    }

    public int getDepth() {
        return depth;
    }

    public Coord getCoordDernierePiecePosee() {
        return coordDernierePiecePosee;
    }

    public Piece getDernierePiecePosee() {
        return dernierePiecePosee;
    }

    public Boolean getAdversaire() {
        return adversaire;
    }

    public Object clone() {
        return listPiece.clone();
    }

    @Override
    public int compareTo(Object o) {
        return this.plateauJeu.compareTo(((NodeData)o).plateauJeu);
    }

    public void setGain(int gain) {
        this.gain = gain;
    }
    
    
    
    
}
