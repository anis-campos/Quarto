/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.model;


/**
 *
 * @author Anis
 */
public class Piece {

    protected Boolean carre;
    protected Boolean grand;
    protected Boolean fonce;
    protected Boolean plein;
    protected String nomFichierPiece;
    //protected Coord coord;
    
    //Les valeurs par défaut sont à TRUE
    public Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein) {
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.nomFichierPiece = this.getName()+".png";
        //this.coord = coord;
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
        //return new Piece(carre, grand, fonce, plein, coord.clone());
        return new Piece(carre, grand, fonce, plein);
    }
    
}
