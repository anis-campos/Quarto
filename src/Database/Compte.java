/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Anis
 */
public class Compte implements Serializable {

    public String pseudo; //unique

    String password;

    public String Nom;
    public String Prenom;
    public Date naissance;

    public Compte(String pseudo, String password, String Nom, String Prenom, Date naissance) {
        this.pseudo = pseudo;
        this.password = password;
        this.Nom = Nom;
        this.Prenom = Prenom;
        this.naissance = naissance;
    }

    @Override
    public String toString() {
        return "Compte utilisateur :"
                + "\n\t pseudo  : " + pseudo
                + "\n\t Nom     : " + Nom
                + "\n\t Prenom  : " + Prenom
                + "\n\t DDN     : " + naissance.toString() + "\n";
    }

    public String NomPrenom() {
        return Nom + " " + Prenom;
    }

}
