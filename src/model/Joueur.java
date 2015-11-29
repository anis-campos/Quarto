/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author timotheetroncy
 */
public class Joueur implements Serializable {
    private final String nom;
    private final Boolean isBot;
    private final NumeroJoueur numeroJoueur;

    public Joueur(String nom, Boolean isBot, NumeroJoueur numeroJoueur) {
        this.nom = nom;
        this.isBot = isBot;
        this.numeroJoueur = numeroJoueur;
    }

    public NumeroJoueur getNumeroJoueur() {
        return numeroJoueur;
     }

    @Override
    public String toString() {
        return getName();
    }
    
    
    
    public String getName(){
        return nom;
    }

    public Boolean isBot() {
        return isBot;
    }
    
    
}
