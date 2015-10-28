/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelquarto;

import static java.lang.Math.abs;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Anis
 */
public class Piece {

    protected Boolean carre;
    protected Boolean grand;
    protected Boolean fonce;
    protected Boolean plein;
    protected String nomPiece;
    protected Coord coord;
    
    //Les valeurs par défaut sont à TRUE
    public Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein, Coord coord) {
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.nomPiece = this.getName()+".png";
        this.coord = coord;
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




    @Override
    protected Piece clone(){
        Piece clone = new Piece(carre, grand, fonce, plein, coord.clone());
        return clone;
    }

    public void setCarre(Boolean carre) {
        this.carre = carre;
    }

    public void setGrand(Boolean grand) {
        this.grand = grand;
    }

    public void setFonce(Boolean fonce) {
        this.fonce = fonce;
    }

    public void setPlein(Boolean plein) {
        this.plein = plein;
    }
    
    
}
