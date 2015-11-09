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
    private final int id;

    public static int instanciationNb = 0;

    //Les valeurs par défaut sont à TRUE
    public Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein) throws Exception {
        ++instanciationNb;
        if (instanciationNb > 16) {
            throw new Exception("Il y a un new Piece en trop dans le code");
        }else{
        this.id = instanciationNb;
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.nomFichierPiece = this.getName() + ".png";
        }
    }

    private Piece(Boolean carre, Boolean grand, Boolean fonce, Boolean plein, int id) {
        this.id = id;
        this.carre = carre;
        this.grand = grand;
        this.fonce = fonce;
        this.plein = plein;
        this.nomFichierPiece = this.getName() + ".png";

    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        String string = "";
        if (carre) {
            string += "CARRE_";
        } else {
            string += "ROND_";
        }
        if (grand) {
            string += "GRAND_";
        } else {
            string += "PETIT_";
        }
        if (fonce) {
            string += "FONCE_";
        } else {
            string += "CLAIR_";
        }
        if (plein) {
            string += "PLEIN";
        } else {
            string += "CREUX";
        }
        return string;
    }

    @Override
    protected Piece clone() throws CloneNotSupportedException {
        return new Piece(carre, grand, fonce, plein, id);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.carre);
        hash = 53 * hash + Objects.hashCode(this.grand);
        hash = 53 * hash + Objects.hashCode(this.fonce);
        hash = 53 * hash + Objects.hashCode(this.plein);
        hash = 53 * hash + Objects.hashCode(this.nomFichierPiece);
        hash = 53 * hash + this.id;
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
    
    
    public int getId() {
        return id;
    }

}
