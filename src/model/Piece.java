/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Objects;


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

    
    //Les valeurs par défaut sont à TRUE
    public Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein) {
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.nomFichierPiece = this.getName()+".png";

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
    protected Piece clone() throws CloneNotSupportedException{
        return new Piece(carre, grand, fonce, plein);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.carre);
        hash = 67 * hash + Objects.hashCode(this.grand);
        hash = 67 * hash + Objects.hashCode(this.fonce);
        hash = 67 * hash + Objects.hashCode(this.plein);
        hash = 67 * hash + Objects.hashCode(this.nomFichierPiece);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Piece other = (Piece) obj;
        if (!Objects.equals(this.carre, other.carre)) {
            return false;
        }
        if (!Objects.equals(this.grand, other.grand)) {
            return false;
        }
        if (!Objects.equals(this.fonce, other.fonce)) {
            return false;
        }
        if (!Objects.equals(this.plein, other.plein)) {
            return false;
        }
        if (!Objects.equals(this.nomFichierPiece, other.nomFichierPiece)) {
            return false;
        }
        return true;
    }

    public int compareTo(Boolean b) {
        return carre.compareTo(b);
    }
    
    
    
}
