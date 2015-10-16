/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Anis
 */
public class Piece {

    protected final Boolean carre;
    protected final Boolean grand;
    protected final Boolean fonce;
    protected final Boolean plein;
    protected Coord coord;
    
    //Les valeurs par défaut sont à TRUE
    public Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein, Coord coord) {
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.coord = coord;
    }

    public boolean move(int xFinal, int yFinal) {
        this.coord.x = xFinal;
        this.coord.y = yFinal;
        return true;
    }
    
    public boolean isMoveOk(int xFinal, int yFinal) {
        return true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        String string = "";
        if(carre){
            string+="CARRE_";
        }else{
            string+="ROND_";
        }
        if(grand){
            string+="GRAND_";
        }else{
            string+="PETIT_";
        }
        if(fonce){
            string+="FONCE_";
        }else{
            string+="CLAIR_";
        }
        if(plein){
            string+="PLEIN";
        }else{
            string+="CREUX";
        }
        return string;
    }


    public int getX() {
        return this.coord.x;
    }

    public int getY() {
        return this.coord.y;
    }

    public List<Coord> availableCoords() {//TO_REFACTOR
        List<Coord> coords = new LinkedList<>();
        for (int colonne = 0; colonne < 8; colonne++) {
            for (int ligne = 0; ligne < 8; ligne++) {
                if (isMoveOk(colonne, ligne)) {
                    coords.add(new Coord(colonne, ligne));
                }
            }
        }
        return coords;
    }



    @Override
    protected Piece clone(){
        Piece clone = new Piece(carre, grand, fonce, plein, coord.clone());
        return clone;
    }
}
