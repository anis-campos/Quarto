/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Network.RMI;

import model.*;

/**
 *
 * @author Anis
 */
public class PartieDistante  {

    Parametre p;
    Joueur J1;
    Joueur J2;
    
    enum Etat{
        PRET,
        INCOMPLET
    };
    
    Etat etat;

    public PartieDistante(Parametre p, Joueur J1, Joueur J2) {
        this.p = p;
        this.J1 = J1;
        this.J2 = J2;
        etat = Etat.PRET;
    }

    public PartieDistante(Parametre p, Joueur J1) {
        this.p = p;
        this.J1 = J1;
        etat = Etat.INCOMPLET;
    }
    
    public void ajouterJ2 (Joueur J2){
        this.J2 = J2;
        etat = Etat.PRET;
    }

  public boolean demarrerPatie(){
    if(etat==Etat.INCOMPLET)
        return false;
    
    //Dammarer la partie
    return true;
}
    
   
    
}
